package com.example.mariatrapicyna.smart_fridge

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Button


class ProsrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prosr_activity)
        val tomenu = findViewById<Button>(R.id.tomenu)
        tomenu.setOnClickListener {
            this.startActivity(Intent(this, MenuActivity::class.java))
        }
    }

}