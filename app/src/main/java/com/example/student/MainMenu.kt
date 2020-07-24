package com.example.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        Attendence.setOnClickListener {
            startActivity(Intent(this,Present::class.java ))
        }

        Reflect.setOnClickListener {
            startActivity(Intent(this,Reflection::class.java))
        }

    }
}
