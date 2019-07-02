package com.example.mariatrapicyna.smart_fridge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)
        var singIn = findViewById<Button>(R.id.singIn)
        var register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        }


    }
}
