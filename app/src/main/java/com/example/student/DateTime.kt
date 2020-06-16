package com.example.student

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_date_time.*

import java.text.SimpleDateFormat
import java.util.*

class DateTime : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var minute = 0
    private var hour = 0
    private var dayOfMonth = 0
    private var year = 0
    private var month = 0


    lateinit var alarmManager: AlarmManager
    lateinit var alarmIntent: PendingIntent

    @TargetApi(Build.VERSION_CODES.O)
    val date = Calendar.Builder()
        .setDate(year, month, dayOfMonth)
        .setTimeOfDay(hour, minute, 0)
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)


        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = PendingIntent.getBroadcast(
            applicationContext, 0,
            Intent(applicationContext, MainMenu::class.java), 0
        )
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.time.time, alarmIntent)

        Monday.setOnClickListener {
            val dialog = MyTimePickerDialog()
            dialog.show(supportFragmentManager, "time_picker")
        }


        Choosedate.setOnClickListener {
            val dialog = MyDatePickerDialog()
            dialog.show(supportFragmentManager, "date_picker")
        }



    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.minute = minute
        this.hour = hourOfDay
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.year = year
        this.dayOfMonth = month
        this.dayOfMonth = dayOfMonth
    }
}