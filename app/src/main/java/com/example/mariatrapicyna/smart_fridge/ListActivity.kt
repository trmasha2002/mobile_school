package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var list: List<Product> = listOf()
        recyclerView.adapter = ProductAdapter(list)

    }
}
