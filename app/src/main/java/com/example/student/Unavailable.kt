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
        var Others = ""

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
                Others = ""
            }
            else if (Option_text == "Others (Please state reason in log)")
            {
                MC = ""
                Others = "1"
            }

            if (Option_text != "Please Select")
            {
                Toast.makeText(applicationContext,"Reason Submited",Toast.LENGTH_SHORT).show()

                val queue2 = Volley.newRequestQueue(this)
                val url = "https://script.google.com/macros/s/AKfycbzHVvfi0NTe4cg18QqNcBsitSI2_Xzdp-XeJy7lZIax26T6WXe9/exec"

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
                        params["OTHERS"] = Others
                        return params
                    }
                }
                queue2.add(stringRequest1)



            }

        }
    }
}
