package com.example.student

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "Reminder"
    val description = "Attendance Notify"
    private val REQUEST_CODE = 50
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent


    private lateinit var biometricManager: BiometricManager
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = ContextCompat.getMainExecutor(this)
        val biometricManager = BiometricManager.from(this)




        biometricPrompt = BiometricPrompt(this,executor,
            object: BiometricPrompt.AuthenticationCallback()
            {
                // 2
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    gotoMenuActivity()
                }
                // 3
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // 4
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext,
                        "Authentication Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        val biometricLoginButton =
            findViewById<Button>(R.id.biometric)
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }


        val Code = findViewById<EditText>(R.id.Password)
        val UserName = findViewById<EditText>(R.id.Student_UserName)


        val sharedPreference: SharedPreference = SharedPreference(this)
        val StudentID = sharedPreference.getValueString("ID")


        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcast::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val pendingIntent1 =
            PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 =
            PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val now = Calendar.getInstance()


        val Hou = now.get(Calendar.HOUR_OF_DAY)
        val min = now.get(Calendar.MINUTE)
        val sec = now.get(Calendar.SECOND)
        val Week = now.get(Calendar.DAY_OF_WEEK)

        var year = now.get(Calendar.YEAR)
        var month = now.get(Calendar.MONTH)
        var day = now.get(Calendar.DAY_OF_MONTH)

        val Nm = month + 1



        var yr = "" + year
        var mh = ""
        var dy = ""

        if (Nm < 10)
        {
            mh += "0" + Nm
        }
        else
        {
            mh += "" + Nm
        }


        if (day < 10) {
            dy += "0" + day
        }
        else
        {
            dy += "" + day
        }

        val date = yr + "-" + mh + "-" + dy

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 18)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)


        alarm.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )


        if ((Hou == 18) && ((min == 0)||(min == 1))) {
            val x = "0"
            sharedPreference.save("x", x)
        }


            if (sharedPreference.getValueString("x") == "0") {
                val cal = Calendar.getInstance()

                cal.timeInMillis = System.currentTimeMillis()
                cal.set(Calendar.HOUR_OF_DAY, 19)
                cal.set(Calendar.MINUTE, 0)
                cal.set(Calendar.SECOND, 0)

                alarm.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    cal.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent1
                )

                val ca = Calendar.getInstance()

                ca.timeInMillis = System.currentTimeMillis()
                ca.set(Calendar.HOUR_OF_DAY, 20)
                ca.set(Calendar.MINUTE, 0)
                ca.set(Calendar.SECOND, 0)

                alarm.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    ca.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent2
                )
            }

            if (sharedPreference.getValueString("x") == "1") {
                alarm.cancel(pendingIntent1)
                alarm.cancel(pendingIntent2)
            }







        SignIn.setOnClickListener {
            if ((sharedPreference.getValueString("name") == (Code.text.toString())) && (sharedPreference.getValueString("ID") == (Student_UserName.text.toString()))
            ) {
                startActivity(Intent(this, MainMenu::class.java))
            } else {
                Toast.makeText(this@MainActivity, "Incorrect password or username",Toast.LENGTH_SHORT).show()
            }
        }

        SignUp.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun gotoMenuActivity()
    {
 val intent = Intent(this,MainMenu::class.java)
        startActivity(intent)

    }



    }








