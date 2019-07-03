package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        supportActionBar?.title = "Back to List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}