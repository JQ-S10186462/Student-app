package com.example.student

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "Reminder"
    val description = "Attendance Notify"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Code = findViewById<EditText>(R.id.Password)
        val UserName = findViewById<EditText>(R.id.Student_UserName)

        val sharedPreference:SharedPreference=SharedPreference(this)

        val show = findViewById<Button>(R.id.notify)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notify.setOnClickListener {

            val intent = Intent(applicationContext, MainMenu::class.java)
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


