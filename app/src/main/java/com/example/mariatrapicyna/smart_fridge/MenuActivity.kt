package com.example.mariatrapicyna.smart_fridge

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val list_product = findViewById<Button>(R.id.listproduct)
        val prosr = findViewById<Button>(R.id.prosr)
        val recipes = findViewById<Button>(R.id.recipes)

        list_product.setOnClickListener {
            this.startActivity(Intent(this, ListActivity::class.java))
        }

    }
}
