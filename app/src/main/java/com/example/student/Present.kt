package com.example.student

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.student.Absent
import kotlinx.android.synthetic.main.activity_present.*

class Present : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_present)

        Absent.setOnClickListener {
            startActivity(Intent(this,Attendance::class.java))
        }
        }
    }
