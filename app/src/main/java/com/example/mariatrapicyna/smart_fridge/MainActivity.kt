package com.example.mariatrapicyna.smart_fridge

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase.getInstance

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val singIn = findViewById<Button>(R.id.singIn)
        val register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        }
        singIn.setOnClickListener {
            if (isSinged()) {

            } else {
                Toast.makeText(applicationContext, "Даннны не корректны", Toast.LENGTH_LONG).show()
                // И пишет где ошибка
            }

        }

        val database = getInstance()
        val myRef = database.reference
        myRef.child("users").setValue("Hello, World!")

    }

    private fun isSinged(): Boolean {
        /*
        * Проверяет корректность данных
        * Допишете, когда доделаете авторизацию :)  */

        return true
    }
}