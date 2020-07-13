package com.example.student

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reflection.*
import java.time.LocalDate
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "Reminder"
    val description = "Attendance Notify"
    private val REQUEST_CODE = 50
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Code = findViewById<EditText>(R.id.Password)
        val UserName = findViewById<EditText>(R.id.Student_UserName)


        val sharedPreference: SharedPreference=SharedPreference(this)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val pendingIntent1 = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var x = "0"


        val calendar = Calendar.getInstance()

        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set (Calendar.HOUR_OF_DAY, 18)
        calendar.set (Calendar.MINUTE, 0)
        calendar.set (Calendar.SECOND,0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)

        val cal = Calendar.getInstance()

        cal.timeInMillis = System.currentTimeMillis()
        cal.set (Calendar.HOUR_OF_DAY, 19)
        cal.set (Calendar.MINUTE, 0)
        cal.set (Calendar.SECOND,0)

        val ca = Calendar.getInstance()

        ca.timeInMillis = System.currentTimeMillis()
        ca.set (Calendar.HOUR_OF_DAY, 20)
        ca.set (Calendar.MINUTE, 0)
        ca.set (Calendar.SECOND,0)

        val now = Calendar.getInstance()
        val date = LocalDate.now()


        var min = now.get(Calendar.MINUTE)
        var Hou = now.get(Calendar.HOUR_OF_DAY)


        if ((Hou == 18) && (min == 0))
        {
            x = "1"
        }

        if (x == "1")
        {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent1)

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                ca.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent2)


        }

        x = sharedPreference.getValueString("x").toString()


        if (x == "0")
        {
            alarmManager.cancel(pendingIntent1)
            alarmManager.cancel(pendingIntent2)
        }

        val show = findViewById<Button>(R.id.notify)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notify.setOnClickListener {

            val intent = Intent(applicationContext, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setContentTitle("Student")
                    .setContentText("Ready for Work?")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("Student")
                    .setContentText("Ready for Work?")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notificationManager.notify(0, builder.build())
            }
        }



        SignIn.setOnClickListener {
            if ((sharedPreference.getValueString("name") == (Code.text.toString())) && (sharedPreference.getValueString("ID") == (Student_UserName.text.toString()))) {
                startActivity(Intent(this, MainMenu::class.java))
            }
                else
                {
                    Toast.makeText(this@MainActivity,"Incorrect UserName or Password", Toast.LENGTH_SHORT ).show()
                }
            }

        SignUp.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
}


