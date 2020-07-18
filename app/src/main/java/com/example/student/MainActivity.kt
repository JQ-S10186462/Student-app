package com.example.student

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reflection.*
import java.time.LocalDate
import java.util.*
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "Reminder"
    val description = "Attendance Notify"
    private val REQUEST_CODE = 50
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo





    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Code = findViewById<EditText>(R.id.Password)
        val UserName = findViewById<EditText>(R.id.Student_UserName)


        val sharedPreference: SharedPreference=SharedPreference(this)
        val x = "0"
        sharedPreference.save("x",x)

        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val pendingIntent1 = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)



        val now = Calendar.getInstance()


        val Hou = now.get(Calendar.HOUR_OF_DAY)
        val min = now.get(Calendar.MINUTE)
        val sec = now.get(Calendar.SECOND)


        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set (Calendar.HOUR_OF_DAY, 15)
        calendar.set (Calendar.MINUTE, 1)
        calendar.set (Calendar.SECOND,0)


        alarm.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)

        if ((Hou == 15) && (min == 0) && (sharedPreference.getValueString("x") == "0") )
        {
           val x = "1"
            sharedPreference.save("x",x)
            val cal = Calendar.getInstance()

            cal.timeInMillis = System.currentTimeMillis()
            cal.set(Calendar.HOUR_OF_DAY, 15)
            cal.set(Calendar.MINUTE, 3)
            cal.set(Calendar.SECOND, 0)

            val ca = Calendar.getInstance()

            ca.timeInMillis = System.currentTimeMillis()
            ca.set(Calendar.HOUR_OF_DAY, 15)
            ca.set(Calendar.MINUTE, 6)
            ca.set(Calendar.SECOND, 0)

            alarm.setRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent1)

            alarm.setRepeating(
                AlarmManager.RTC_WAKEUP,
                ca.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent2)
        }


        if (sharedPreference.getValueString("x") == "0")
        {
            alarm.cancel(pendingIntent1)
            alarm.cancel(pendingIntent2)
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


