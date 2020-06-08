package com.example.student

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    val channelId ="Reminder"
    val description ="Ready for work"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)

        SignUp.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
            SignIn.setOnClickListener {
                startActivity(Intent (this, MainMenu ::class.java) )
            }
        }

    }

