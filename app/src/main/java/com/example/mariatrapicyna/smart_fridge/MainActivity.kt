package com.example.mariatrapicyna.smart_fridge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabase.getInstance
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)
        var singIn = findViewById<Button>(R.id.singIn)
        var register = findViewById<Button>(R.id.register)

        register.setOnClickListener(View.OnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        })
    }
        setContentView(R.layout.activity_main)
        val database = getInstance()
        val myRef = database.reference
        myRef.child("users").setValue("Hello, World!")

    }
}