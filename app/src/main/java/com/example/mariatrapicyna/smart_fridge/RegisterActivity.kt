package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.email)
        val firstPassword = findViewById<EditText>(R.id.firstPassword)
        val secondPassword = findViewById<EditText>(R.id.secondPassword)
        val username = findViewById<EditText>(R.id.username)
        val register = findViewById<Button>(R.id.register)


        register.setOnClickListener {
            if (firstPassword.text == secondPassword.text) {

            } else {
                Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            }
        }
    }
}
