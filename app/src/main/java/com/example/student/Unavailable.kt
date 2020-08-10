package com.example.student

import androidx.appcompat.app.AppCompatActivity
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
        var Postal = sharedPreference.getValueString("Postal")
        var Npis = sharedPreference.getValueString("Supervisor")


        var Option = findViewById<Spinner>(R.id.spinner)
        var script = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"
        var Status = ""
        var input = ""

        var MC = ""
        var Onsite = ""
        var Suspend = ""
        var WFH = ""
        var Absent = ""
        var Off = ""


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
                MC = "1"
                Onsite = ""
                Suspend = ""
                WFH = ""
                Absent = ""
                Off = ""

            }
            else if (Option_text == "OnSite")
            {
                MC = ""
                Onsite = "1"
                Suspend = ""
                WFH = ""
                Absent = ""
                Off = ""

            }
            else if (Option_text == "Off Day")
            {
                MC = ""
                Onsite = ""
                Suspend = ""
                WFH = ""
                Absent = ""
                Off = "1"
            }
            else if (Option_text == "Absent")
            {
                MC = ""
                Onsite = ""
                Suspend = ""
                WFH = ""
                Absent = "1"
                Off = ""
            }
            else if (Option_text == "Working From Home")
            {
                MC = ""
                Onsite = ""
                Suspend = ""
                WFH = "1"
                Absent = ""
                Off = ""
            }
            else if (Option_text == "Internship Suspended")
            {
                MC = ""
                Onsite = ""
                Suspend = "1"
                WFH = ""
                Absent = ""
                Off = ""
            }

            if (Option_text != "Please Select")
            {
                Toast.makeText(applicationContext,"Reason Submited",Toast.LENGTH_SHORT).show()

                val queue2 = Volley.newRequestQueue(this)
                val url = "https://script.google.com/macros/s/AKfycbxi47Ee3vq94_lU5-46wwLf2qV2bHUdFg0O-l4QOYk2qKgHy0Y/exec"

                val stringRequest1 = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> {
                    },
                    Response.ErrorListener {}) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["action"] = "StudentInput"
                        params["STUDENTID"] = ID_text.toString()
                        params ["POSTAL"] = Postal.toString()
                        params ["NPISID"] = Npis.toString()
                        params ["MC"] = MC
                        params["ABSENT"] = Absent
                        params ["INTERNSHIPSUSPENDED"] = Suspend
                        params ["WFH"] = WFH
                        params ["OFFDAY"] = Off
                        params ["ONSITE"] = Onsite
                        return params
                    }
                }
                queue2.add(stringRequest1)



            }

        }
    }
}
