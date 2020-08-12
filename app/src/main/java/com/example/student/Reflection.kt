package com.example.student

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_reflection.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Reflection : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflection)

        val sharedPreference: SharedPreference = SharedPreference(this)


        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val pendingIntent1 = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)




        var script = "https://script.google.com/macros/s/AKfycbzjmCkpW5LuFOvdPbdVScr2IPNFTFQ3ZjubgIL7Jpv7xdKhvFnY/exec?"




        Logging.setOnClickListener {


            val ID = sharedPreference.getValueString("ID")
            val Postal = sharedPreference.getValueString("Postal")
            val Npis = sharedPreference.getValueString("Supervisor")

            var text = findViewById<TextView>(R.id.timenow)
            var Reflect = findViewById<EditText>(R.id.dailylog)

            val today = Calendar.getInstance()
            val date = LocalDate.now()


            var year = today.get(Calendar.YEAR)
            var month = today.get(Calendar.MONTH)
            var day = today.get(Calendar.DAY_OF_MONTH)

            val Nm = month + 1



            var yr = "" + year
            var mh = ""
            var dy = ""

            if (Nm < 10)
            {
             mh += "0" + Nm
            }
            else
            {
                mh += "" + Nm
            }


            if (day < 10) {
                dy += "0" + day
            }
            else
            {
                dy += "" + day
            }




            val Log = Reflect.editableText.toString()



            if (Log == "") {
                Toast.makeText(
                    applicationContext,
                    "Please enter your reflection",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
               val x = "1"
                sharedPreference.save("x",x)


                Toast.makeText(
                    applicationContext,
                    "Relfection Entered", Toast.LENGTH_SHORT).show()

                val queue2 = Volley.newRequestQueue(this)
                val url = "https://script.google.com/macros/s/AKfycbxi47Ee3vq94_lU5-46wwLf2qV2bHUdFg0O-l4QOYk2qKgHy0Y/exec"

                val stringRequest1 = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> {
                    },
                    Response.ErrorListener {}) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["action"] = "writereflect"
                        params["Date"] = yr + "-" + mh + "-" + dy
                        params ["STUDENTID"] = ID.toString()
                        params ["REFLECTION"] = Log
                        return params
                    }
                }
                queue2.add(stringRequest1)

                startActivity(Intent(this, MainActivity::class.java))


            }


        }
    }
}
