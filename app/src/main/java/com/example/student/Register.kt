package com.example.student

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray


class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val NPISID = findViewById<EditText>(R.id.NPISID)
        val ID = findViewById<EditText>(R.id.Student)
        val Postal = findViewById<EditText>(R.id.Postal)
        val Course = findViewById<EditText>(R.id.Course)
        val Submit  = findViewById<Button>(R.id.Submit)

        val jsonArray = JSONArray()
        jsonArray.put(NPISID)
        jsonArray.put(ID)
        jsonArray.put(Postal)
        jsonArray.put(Course)


        val mRequestBody = jsonArray.toString()

        Next.setOnClickListener {
         startActivity(Intent(this, DateTime::class.java))
        }

        Submit.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://script.google.com/macros/s/AKfycbwsfWSeDTVMrKCyIB7CXZxJaWjxGJtVFjdBHWWV1-d4iJvWbyw/exec?"


            val stringRequest = object: StringRequest(Request.Method.POST,url,
                Response.Listener<String> {
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {Toast.makeText(applicationContext,"Fail",Toast.LENGTH_SHORT).show()}) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params.put("NPISID_Label", NPISID.toString())
                        params.put("Course_Label", Course.toString())
                        params.put("StudentID_Label", ID.toString())
                        params.put("Postal_Label",Postal.toString())
                        return params
                    }

                }
            queue.add(stringRequest)
                }

            }


            }







