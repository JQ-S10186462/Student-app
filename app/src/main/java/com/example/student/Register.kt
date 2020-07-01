package com.example.student

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray


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


            val Postal_text = Postal.editableText.toString()
            val Course_text = Course.editableText.toString()

            if ((Student_ID == "") || (NPISID_text == "") || (Postal_text == "") || (Course_text == "")) {
                Toast.makeText(applicationContext, "Please Fill in the Blanks!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val queue = Volley.newRequestQueue(this)
                var input = ""
                input += script + "NPISID=" + NPISID_text + "&Course=" + Course_text + "&StudentID=s" + Student_ID + "&Postal=" + Postal_text + "&Comment=Setup"


                val stringRequest = object : StringRequest(Request.Method.GET, input,
                    Response.Listener<String> {
                        Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                    }) {

                }



                queue.add(stringRequest)
            }
        }

            }


            }







