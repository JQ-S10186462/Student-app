package com.example.student

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat

class AlarmBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel
            val name = "Remind"
            val descriptionText = "Reflection Alarm"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel("ReflectId", name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        // Create the notification to be shown
        val mBuilder = NotificationCompat.Builder(context!!, "AlarmId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Student")
            .setContentText("Please do reflection")
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context, // Context from onReceive method.
                    50,
                    Intent(
                        context,
                        MainActivity::class.java
                    ), // Activity you want to launch onClick.
                    0
                )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)


        // Get the Notification manager service
        val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        // Show a notification
        am.notify(0, mBuilder.build())
    }
}