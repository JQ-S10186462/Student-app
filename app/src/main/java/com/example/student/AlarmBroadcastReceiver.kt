package com.example.student

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast

class AlarmBroadcastReceiver : BroadcastReceiver () {


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "Notify"
    val description = "Attendance"


        override fun onReceive(context: Context, intent: Intent) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // Create the NotificationChannel
                val name = "Alarm"
                val descriptionText = "Attendance Alarm"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel("AlarmId", name, importance)
                mChannel.description = descriptionText
                val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }

            // Create the notification to be shown
            val mBuilder = NotificationCompat.Builder(context!!, "AlarmId")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Student")
                .setContentText("Please do attendance")
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(
                    context, // Context from onReceive method.
                    20,
                    Intent(context, MainActivity::class.java), // Activity you want to launch onClick.
                    0
                )
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)


            // Get the Notification manager service
            val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



            // Show a notification
            am.notify(0,mBuilder.build())
        }

    }


