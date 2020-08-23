package com.example.student

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
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

   private lateinit var aFusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var FusedLocationProviderClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_present)

        aFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        val sharedPreference: SharedPreference = SharedPreference(this)
        val Postal = sharedPreference.getValueString("Postal")


        val longitude = sharedPreference.getValueString("long").toString().toDouble()
        val latitude = sharedPreference.getValueString("lat").toString().toDouble()


        var Maxlongitute = longitude + 0.0003
        var Minlongitute = longitude - 0.0003

        var Maxlatitude = latitude + 0.0003
        var Minlatitude = latitude - 0.0003




        Status.setOnClickListener {
            startActivity(Intent(this, Unavailable::class.java))
        }


        Avaliable.setOnClickListener {


            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
                FusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {

                            var longnow = location.longitude
                            var latnow = location.latitude

                            var x = if (((longnow >= Minlongitute) && (longnow <= Maxlongitute))) {
                                1
                            } else {
                                0
                            }

                            var y = if ((latnow >= Minlatitude) && (latnow <= Maxlatitude)) {
                                1
                            } else {
                                0
                            }


                            val ID = sharedPreference.getValueString("ID")
                            val NPISID = sharedPreference.getValueString("Supervisor")

                            val queue = Volley.newRequestQueue(this)
                            val input =
                                "https://script.google.com/macros/s/AKfycbxi47Ee3vq94_lU5-46wwLf2qV2bHUdFg0O-l4QOYk2qKgHy0Y/exec"

                            if ((x == 1) && (y == 1)) {
                                Toast.makeText(applicationContext, "Attendance sent", Toast.LENGTH_SHORT).show()
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
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Wrong Location",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        } else {
                            Toast.makeText(applicationContext, "Null", Toast.LENGTH_SHORT).show()
                        }

                    }


            } else
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_ID)
            }

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

     fun updatewithLocation(location : Location)
    {
        val sharedPreference: SharedPreference = SharedPreference(this)
        sharedPreference.save("latNow",location.latitude.toString())
        sharedPreference.save("longNow",location.longitude.toString())




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

