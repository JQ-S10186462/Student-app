package com.example.student

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_date_time.*

import java.text.SimpleDateFormat
import java.util.*


class DateTime : AppCompatActivity() {

    private var minute = 0
    private var hour = 0
    private var dayOfMonth = 0
    private var year = 0
    private var month = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)


        val sharedPreference: SharedPreference = SharedPreference(this)

        val Button = findViewById<Button>(R.id.time)


        val Monday = findViewById<Spinner>(R.id.Mon)
        val Tuesday = findViewById<Spinner>(R.id.Tue)
        val Wednesday = findViewById<Spinner>(R.id.Wed)
        val Thursday = findViewById<Spinner>(R.id.Thur)
        val Friday = findViewById<Spinner>(R.id.Fri)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val intent1 = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent1 =
            PendingIntent.getBroadcast(this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        val intent2 = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent2 =
            PendingIntent.getBroadcast(this, 2, intent2, PendingIntent.FLAG_UPDATE_CURRENT)

        val intent3 = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent3 =
            PendingIntent.getBroadcast(this, 3, intent3, PendingIntent.FLAG_UPDATE_CURRENT)

        val intent4 = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent4 =
            PendingIntent.getBroadcast(this, 4, intent4, PendingIntent.FLAG_UPDATE_CURRENT)


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.Switch, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        Mon.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        Tue.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        Wed.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        Thur.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        Fri.adapter = adapter


        var Hour_Day = findViewById<EditText>(R.id.Ho)
        var Minute_day = findViewById<EditText>(R.id.Mi)






        buttonsubmit.setOnClickListener {

            val today = Calendar.getInstance()

            val day = today.get(Calendar.DAY_OF_WEEK)
            val week = today.get(Calendar.WEEK_OF_YEAR)
            val hour = today.get(Calendar.HOUR_OF_DAY)
            val minute = today.get(Calendar.MINUTE)

            val Mon_Text = Monday.selectedItem.toString()
            val Tue_Text = Tuesday.selectedItem.toString()
            val Wed_Text = Wednesday.selectedItem.toString()
            val Thu_Text = Thursday.selectedItem.toString()
            val Fri_Text = Friday.selectedItem.toString()


            val Min = Minute_day.editableText.toString()
            val Hou = Hour_Day.editableText.toString()

            val H = Hou.toInt()
            val M = Min.toInt()
            var x = 0


            if ((H >= 24) || (M >= 60)) {
                Toast.makeText(applicationContext, "Invalid Time", Toast.LENGTH_SHORT).show()
            } else if (Hou == "") {
                Toast.makeText(applicationContext, "Fill in the blanks", Toast.LENGTH_SHORT).show()
            }
            else if (Min == "") {
                Toast.makeText(applicationContext, "Fill in the blanks", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Alarm Set", Toast.LENGTH_SHORT).show()
                if (Mon_Text == "ON") {
                    val monday = Calendar.getInstance()
                    monday.timeInMillis = System.currentTimeMillis()
                    monday.set(Calendar.HOUR_OF_DAY, H)
                    monday.set(Calendar.MINUTE, M)
                    monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

                    if ((day > 2) || ((day == 2) && ((hour > H) || ((hour == H) && (minute >= M))))) {
                        val w = week + 1
                        monday.set(Calendar.WEEK_OF_YEAR, w)
                    }

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        monday.timeInMillis,
                        AlarmManager.INTERVAL_DAY * 7,
                        pendingIntent
                    )
                } else {
                    alarmManager.cancel(pendingIntent)
                    val monday = "0"
                    sharedPreference.save("Monday", monday)
                }


                if (Tue_Text == "ON") {
                    val Min = Minute_day.editableText.toString()
                    val Hou = Hour_Day.editableText.toString()

                    val H = Hou.toInt()
                    val M = Min.toInt()


                    val tuesday = Calendar.getInstance()
                    tuesday.timeInMillis = System.currentTimeMillis()
                    tuesday.set(Calendar.HOUR_OF_DAY, H)
                    tuesday.set(Calendar.MINUTE, M)
                    tuesday.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)


                    if ((day > 3) || ((day == 3) && ((hour > H) || ((hour == H) && (minute >= M))))) {
                        val w = week + 1
                        tuesday.set(Calendar.WEEK_OF_YEAR, w)
                    }


                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        tuesday.timeInMillis,
                        AlarmManager.INTERVAL_DAY * 7,
                        pendingIntent1
                    )
                } else {
                    alarmManager.cancel(pendingIntent1)
                    val tuesday = "0"
                    sharedPreference.save("Tuesday", tuesday)
                }


                if (Wed_Text == "ON") {
                    val Min = Minute_day.editableText.toString()
                    val Hou = Hour_Day.editableText.toString()

                    val H = Hou.toInt()
                    val M = Min.toInt()

                    val wednesday = Calendar.getInstance()
                    wednesday.timeInMillis = System.currentTimeMillis()
                    wednesday.set(Calendar.HOUR_OF_DAY, H)
                    wednesday.set(Calendar.MINUTE, M)
                    wednesday.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)

                    if ((day > 4) || ((day == 4) && ((hour > H) || ((hour == H) && (minute >= M))))) {
                        val w = week + 1
                        wednesday.set(Calendar.WEEK_OF_YEAR, w)
                    }

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        wednesday.timeInMillis,
                        AlarmManager.INTERVAL_DAY * 7,
                        pendingIntent2
                    )
                } else {
                    alarmManager.cancel(pendingIntent2)
                    val wednesday = "0"
                    sharedPreference.save("Wednesday", wednesday)
                }


                if (Thu_Text == "ON") {

                    val thursday = Calendar.getInstance()
                    thursday.timeInMillis = System.currentTimeMillis()
                    thursday.set(Calendar.HOUR_OF_DAY, H)
                    thursday.set(Calendar.MINUTE, M)
                    thursday.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)

                    if ((day > 5) || ((day == 5) && ((hour > H) || ((hour == H) && (minute >= M))))) {
                        val w = week + 1
                        thursday.set(Calendar.WEEK_OF_YEAR, w)
                    }

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        thursday.timeInMillis,
                        AlarmManager.INTERVAL_DAY * 7,
                        pendingIntent3
                    )
                } else {
                    alarmManager.cancel(pendingIntent3)
                    val thursday = "0"
                    sharedPreference.save("Thursday", thursday)
                }



                if (Fri_Text == "ON") {
                    val friday = Calendar.getInstance()
                    friday.timeInMillis = System.currentTimeMillis()
                    friday.set(Calendar.HOUR_OF_DAY, H)
                    friday.set(Calendar.MINUTE, M)
                    friday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)

                    if ((day > 6) || ((day == 6) && ((hour > H) || ((hour == H) && (minute >= M))))) {
                        val w = week + 1
                        friday.set(Calendar.WEEK_OF_YEAR, w)
                    }

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        friday.timeInMillis,
                        AlarmManager.INTERVAL_DAY * 7,
                        pendingIntent4
                    )
                } else {
                    alarmManager.cancel(pendingIntent4)
                    val friday = "0"
                    sharedPreference.save("Friday", friday)
                }

            }

        }

    }
}







