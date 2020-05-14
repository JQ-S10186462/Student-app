package com.example.student

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignUp.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
            SignIn.setOnClickListener {
                startActivity(Intent (this, Attendance ::class.java) )
            }
        }

    }

