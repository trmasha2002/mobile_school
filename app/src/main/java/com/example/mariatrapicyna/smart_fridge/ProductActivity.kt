package com.example.mariatrapicyna.smart_fridge

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.core.Constants
import android.support.design.widget.FloatingActionButton as FloatingActionButton1


class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var mDatabase = FirebaseDatabase.getInstance().reference

    val fab = findViewById<View>(R.id.fab) as FloatingActionButton1
    fab.setOnClickListener{view ->
        //Show Dialog here to add new Item
        addNewItemDialog()
    }

        var toDoItemList = mutableListOf<ToDoItem>()
        var adapter = ToDoItemAdapter(this, toDoItemList!!)

    }
    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)
        val itemEditText = EditText(this)
        alert.setMessage("Введите название продукта")
        alert.setTitle("Доовляем новый продукт")
        alert.setView(itemEditText)
        alert.setPositiveButton("Submit") { dialog, positiveButton ->
            val todoItem = ToDoItem.create()
            todoItem.itemText = itemEditText.text.toString()
            todoItem.done = true
            var mDatabase = FirebaseDatabase.getInstance().reference
            var mAuth = FirebaseAuth.getInstance()
            val userId = mAuth!!.currentUser!!.uid
            //We first make a push so that a new item is made with a unique ID
            val newItem = mDatabase.child("Users").child(userId).child("products").push()

            newItem.setValue(todoItem)
            dialog.dismiss()
            Toast.makeText(this, "Успешно добавлено ", Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }

}

class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDoItem>) : BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val objectId: String = itemList.get(position).objectId as String
        val itemText: String = itemList.get(position).itemText as String
        val done: Boolean = itemList.get(position).done as Boolean
        val view: View
        val vh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.row_items, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.label.text = itemText
        vh.isDone.isChecked = done
        return view
    }
    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }
    override fun getItemId(index: Int): Long {
        return index.toLong()
    }
    override fun getCount(): Int {
        return itemList.size
    }
    private class ListRowHolder(row: View?) {
        val label: TextView = row!!.findViewById<TextView>(R.id.tv_item_text) as TextView
        val isDone: CheckBox = row!!.findViewById<CheckBox>(R.id.cb_item_is_done) as CheckBox
        val ibDeleteObject: ImageButton = row!!.findViewById<ImageButton>(R.id.iv_cross) as ImageButton
    }
}