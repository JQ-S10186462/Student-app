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
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)


        val Button = findViewById<Button>(R.id.time)
        val textView     = findViewById<TextView>(R.id.Timetext)

        Button.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
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
