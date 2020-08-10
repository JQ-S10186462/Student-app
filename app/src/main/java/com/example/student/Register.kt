package com.example.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*


class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val sharedPreference:SharedPreference=SharedPreference(this)

        var NPISID = findViewById<EditText>(R.id.NPISID)
        var ID = findViewById<EditText>(R.id.Student)
        var Postal = findViewById<EditText>(R.id.Postal)
        var Course = findViewById<EditText>(R.id.Course)
        var Submit  = findViewById<Button>(R.id.Submit)
        var script  = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"



        Next.setOnClickListener {
         startActivity(Intent(this, Password::class.java))
        }

        Submit.setOnClickListener {
            val Student_ID = ID.editableText.toString()
            sharedPreference.save("ID", Student_ID)

            val NPISID_text = NPISID.editableText.toString()
            sharedPreference.save("Supervisor", NPISID_text)

            val Email = Student_ID + "@connect.np.edu.sg"

            val Postal_text = Postal.editableText.toString()
            sharedPreference.save("Postal",Postal_text)

            val Course_text = Course.editableText.toString()


                if ((Student_ID == "") || (NPISID_text == "") || (Postal_text == "") || (Course_text == "")) {
                    Toast.makeText(applicationContext, "Please Fill in the Blanks!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Info Submitted", Toast.LENGTH_SHORT).show()

                val queue2 = Volley.newRequestQueue(this)
                val url = "https://script.google.com/macros/s/AKfycbxi47Ee3vq94_lU5-46wwLf2qV2bHUdFg0O-l4QOYk2qKgHy0Y/exec"

                val stringRequest1 = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> {
                    },
                    Response.ErrorListener {}) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["action"] = "RegisterStudent"
                        params["NPISID"] = NPISID_text
                        params["COURSE"] = Course_text
                        params ["STUDENTID"] = Student_ID
                        params ["POSTAL"] = Postal_text
                        params ["EMAIL"] = Email
                        return params
                    }
                }
                queue2.add(stringRequest1)


                    val queue = Volley.newRequestQueue(this)
                    val script = "https://developers.onemap.sg/commonapi/search?searchVal="
                    var input = ""
                    var string = ""

                    input +=  script + Postal_text + "&returnGeom=Y&getAddrDetails=N&pageNum=1"



                    val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, input, null,
                        Response.Listener { response ->
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






            }
        }

            }


            }







