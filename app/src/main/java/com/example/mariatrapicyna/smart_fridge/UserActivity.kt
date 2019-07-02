package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val email = findViewById<TextView>(R.id.email)
        val username = findViewById<TextView>(R.id.username)
        val oldPassword = findViewById<EditText>(R.id.oldPassword)
        val firstNewPassword = findViewById<EditText>(R.id.firstNewPassword)
        val secondNewPassword = findViewById<EditText>(R.id.secondNewPassword)
        val changePassword = findViewById<Button>(R.id.changePassword)


        changePassword.setOnClickListener {
            if (firstNewPassword.text == secondNewPassword.text && firstNewPassword.text != oldPassword.text) {
                //Тут что-то происходит :)
            } else if (firstNewPassword.text != secondNewPassword.text) {
                Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            } else if (firstNewPassword.text == secondNewPassword.text && firstNewPassword.text == oldPassword.text) {
                Toast.makeText(applicationContext, "Новый пароль совпадает с предыдущим", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Даннны не корректны", Toast.LENGTH_LONG).show()
            }
        }
    }
}
