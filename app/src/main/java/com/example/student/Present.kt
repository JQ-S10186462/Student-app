package com.example.student

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_present.*

class Present : AppCompatActivity() {

    var script = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_present)

        val sharedPreference:SharedPreference= SharedPreference(this)

        Absent.setOnClickListener {
            startActivity(Intent(this,NotPresent::class.java))
        }

        Avaliable.setOnClickListener {
          val ID = sharedPreference.getValueString("ID")
          val NPISID = sharedPreference.getValueString("Supervisor")

          val queue = Volley.newRequestQueue(this)
          var input = ""
           input += script + "NPISID=" + NPISID + "&Course=*&StudentID=S" + ID + "&Present=1"

          val stringRequest = object: StringRequest(Request.Method.GET,input,
              Response.Listener <String> {
                  Toast.makeText(applicationContext,"Attendance Submitted",Toast.LENGTH_SHORT).show()
              },
              Response.ErrorListener {
                  Toast.makeText(applicationContext,"Please try again",Toast.LENGTH_SHORT).show()
              }
              ) {}
                  queue.add(stringRequest)

        }



        }
    }
