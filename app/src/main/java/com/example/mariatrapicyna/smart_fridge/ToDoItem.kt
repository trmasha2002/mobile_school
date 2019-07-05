package com.example.mariatrapicyna.smart_fridge

class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }
    var objectId: String? = null
    var itemText: String? = null
    var year: String? = null
    var month: String? = null
    var day: String? = null
    var done: Boolean? = false
}