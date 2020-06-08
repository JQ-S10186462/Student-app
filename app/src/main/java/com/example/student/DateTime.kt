package com.example.student

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_date_time.*

import java.text.SimpleDateFormat
import java.util.*

class DateTime : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)

        Choosedate.setOnClickListener {
            val cal = Calendar.getInstance()
            val y = cal.get(Calendar.YEAR)
            val m = cal.get(Calendar.MONTH)
            val d = cal.get(Calendar.DAY_OF_MONTH)


            val datepickerdialog:DatePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                date.setText("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)
            }, y, m, d)

            datepickerdialog.show()
        }

        Monday.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Mon.setText(String.format("%02d:%02d", hourOfDay , minute));
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()


        }

        Tuesday.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Tue.setText(String.format("%02d:%02d", hourOfDay , minute));
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()


        }

        Wednesday.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Wed.setText(String.format("%02d:%02d", hourOfDay , minute));
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()


        }

        Thursday.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Thu.setText(String.format("%02d:%02d", hourOfDay , minute));
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()


        }

        Friday.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Fri.setText(String.format("%02d:%02d", hourOfDay , minute));
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()


        }
    }
}
