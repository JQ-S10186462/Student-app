package com.example.student

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_password.*
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class Password : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        val sharedPreference:SharedPreference=SharedPreference(this)



        val Password = findViewById<EditText>(R.id.Password_text)
        val BtnSave = findViewById<Button>(R.id.save_password)
        val filename = "Code"

        Next_Activity.setOnClickListener {
            startActivity(Intent(this, DateTime::class.java))
        }

        save_password.setOnClickListener {
            if (Password.text.toString() == "") {
                Toast.makeText(this@Password, "Password Empty", Toast.LENGTH_SHORT).show()
            }
            else {
                val name = Password.editableText.toString()
                sharedPreference.save("name", name)
                Toast.makeText(this@Password, "Password Saved", Toast.LENGTH_SHORT).show()
            }

        }


    }

    }

