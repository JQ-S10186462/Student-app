package com.example.student

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_present.*
import androidx.core.app.ActivityCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.gms.location.*

class Present : AppCompatActivity() {

    var script = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"
    var LOCATION_PERMISSION_ID = 10

   lateinit var aFusedLocationProviderClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_present)

        aFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        val sharedPreference: SharedPreference = SharedPreference(this)
        val Postal = sharedPreference.getValueString("Postal")
        val textView = findViewById<TextView>(R.id.textView9)

        val longitute = sharedPreference.getValueString("long").toString().toDouble()
        val latitude = sharedPreference.getValueString("lat").toString().toDouble()


        var Maxlongitute = longitute + 0.005
        var Minlongitute = longitute - 0.005

        var Maxlatitude = latitude + 0.005
        var Minlatitude = latitude - 0.005

        var y = 0
        var x = 0


        Status.setOnClickListener {
            startActivity(Intent(this, Unavailable::class.java))
        }


        Avaliable.setOnClickListener {

            getLocation()

            val long = sharedPreference.getValueString("longNow").toString().toDouble()
            val lat = sharedPreference.getValueString("latNow").toString().toDouble()


          if (((long >= Minlongitute) && (long <= Maxlongitute)))
          {
               x = 1
          }
            else
          {
              x = 0
          }

         if ((lat >= Minlatitude) && (lat <= Maxlatitude))
         {
             y = 1
         }
            else
         {
              y = 0
         }

            val ID = sharedPreference.getValueString("ID")
            val NPISID = sharedPreference.getValueString("Supervisor")

            val queue = Volley.newRequestQueue(this)
            val input = "https://script.google.com/macros/s/AKfycbzHVvfi0NTe4cg18QqNcBsitSI2_Xzdp-XeJy7lZIax26T6WXe9/exec"

            if ((x == 1) && (y == 1)) {
                val stringRequest1 = object : StringRequest(
                    Request.Method.POST, input,
                    Response.Listener<String> {
                    },
                    Response.ErrorListener {}) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["action"] = "StudentInput"
                        params["STUDENTID"] = ID.toString()
                        params["POSTAL"] = Postal.toString()
                        params["NPISID"] = NPISID.toString()
                        params["PRESENT"] = "1"
                        return params
                    }
                }
                queue.add(stringRequest1)
            }
            else
            {
                Toast.makeText(applicationContext,"Wrong Location",Toast.LENGTH_SHORT).show()
            }


        }

        button.setOnClickListener{
            val Postal = sharedPreference.getValueString("Postal")
            val textView = findViewById<TextView>(R.id.textView9)




            val queue = Volley.newRequestQueue(this)
            val script = "https://developers.onemap.sg/commonapi/search?searchVal="
            var url = ""
            var string = ""

            url +=  script + Postal + "&returnGeom=Y&getAddrDetails=N&pageNum=1"



            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    textView.text = "Response %s".format(response.toString())
                    var one = response.toString()
                    var loc = one.split(",")
                    var lon = loc[7].split(":")
                    var la = loc[6].split(":")

                    val long = lon[1].split('"')
                    val lat = la[1].split('"')




                    sharedPreference.save("lat",lat[1])
                    sharedPreference.save("long",long[1])
                },
                Response.ErrorListener {})
            {}
          queue.add(jsonObjectRequest)



            val longitute = sharedPreference.getValueString("long").toString().toInt()
          val latitude = sharedPreference.getValueString("lat").toString().toInt()


            var Maxlongitute = longitute + 0.005
            var Minlongitute = longitute - 0.005

            var Maxlatitude = latitude + 0.005
            var Minlatitude = latitude - 0.005

        }
    }

    private fun getLocation()
    {
        if(checkLocationPermission())
        {
            updateNewLocation()
        } else
        {
            askPermission()
        }
    }

    private fun updateNewLocation()
    {
        var aLocationRequest = LocationRequest()
        aLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        aLocationRequest.interval = 10000
        aLocationRequest.fastestInterval = 5000

        aFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        aFusedLocationProviderClient.requestLocationUpdates(aLocationRequest,aLocationCallback,
        Looper.myLooper())

    }


    private  val aLocationCallback = object : LocationCallback()
    {
        override fun onLocationResult(locationResult: LocationResult) {
        var aLastLocation = locationResult.lastLocation
            updatewithLocation(aLastLocation)
        }
    }

    private fun updatewithLocation(location : Location)
    {
        val sharedPreference: SharedPreference = SharedPreference(this)
        sharedPreference.save("latNow",location.latitude.toString())
        sharedPreference.save("longNow",location.longitude.toString())

        textView6.text = "long:" + location.longitude.toString()
        textView3.text = "lat:" + location.latitude.toString()

    }

    private fun checkLocationPermission ():Boolean
    {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private fun askPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_ID)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_ID)
        {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
           updateNewLocation()
            }
            else
            {
                Toast.makeText(applicationContext,"Please give location permission",Toast.LENGTH_SHORT).show()
            }
        }

    }


    }

