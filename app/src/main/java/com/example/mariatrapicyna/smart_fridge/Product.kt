package com.example.mariatrapicyna.smart_fridge

import java.util.*

data class Product constructor(
    val _name: String,
    val _shelfLife: Date,
    val _butTime: Date,
    val _count: Int
)