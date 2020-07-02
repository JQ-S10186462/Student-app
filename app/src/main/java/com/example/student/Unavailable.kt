package com.example.student

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_unavaliable.*

class Unavailable : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unavaliable)

        val sharedPreference:SharedPreference= SharedPreference(this)

        var NPISID_text = sharedPreference.getValueString("Supervisor")
        var ID_text = sharedPreference.getValueString("ID")

        var Option = findViewById<Spinner>(R.id.spinner)
        var script = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"
        var Status = ""
        var input = ""

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.Choice, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter

        Send.setOnClickListener {
            val Option_text = Option.selectedItem.toString()

            if (Option_text == "Please Select") {
                Toast.makeText(this@Unavailable, "Please Choose Option",Toast.LENGTH_SHORT).show()
            }
            else if (Option_text == "MC")
            {
                input = ""
                Status = ""
                Status = "&MC=1"
            }
            else if (Option_text == "Others (Please state reason in log)")
            {
                input = ""
                Status = ""
                Status = "&Others=1"
            }

            if (Option_text != "Please Select")
            {
                val queue = Volley.newRequestQueue(this)
                input += script + "NPISID=" + NPISID_text + "&Course=*&StudentID=s" + ID_text + Status

                val stringRequest = object : StringRequest(Request.Method.GET, input,
                    Response.Listener <String> {
                        Toast.makeText(applicationContext,"Reason Submited",Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(applicationContext,"Please try again",Toast.LENGTH_SHORT).show()
                    })
                    {}

                stringRequest.setRetryPolicy(
                    DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                )

                queue.add(stringRequest)

            }

        }
    }
}
