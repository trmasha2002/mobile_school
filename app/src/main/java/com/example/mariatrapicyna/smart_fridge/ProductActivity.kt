package com.example.mariatrapicyna.smart_fridge

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
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
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.HashMap
import android.support.design.widget.FloatingActionButton as FloatingActionButton1
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout





interface ItemRowListener {
    fun onItemDelete(itemObjectId: String) {
    }
}

class ProductActivity : AppCompatActivity(), ItemRowListener {
    override fun onItemDelete(itemObjectId: String) {
        //get child reference in database via the ObjectID
        var mDatabase = FirebaseDatabase.getInstance().reference
        var mAuth = FirebaseAuth.getInstance()
        val userId = mAuth!!.currentUser!!.uid
        val itemReference = mDatabase.child("Users").child(userId).child("products").child(itemObjectId)
        //deletion can be done via removeValue() method
        itemReference.removeValue()
        this.startActivity(Intent(this, ProductActivity::class.java))
    }
    var toDoItemList: MutableList<ToDoItem>? = null
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null
    var itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            addDataToList(dataSnapshot)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var mDatabase = FirebaseDatabase.getInstance().reference

    val fab = findViewById<View>(R.id.fab) as FloatingActionButton1
        listViewItems = findViewById<View>(R.id.items_list) as ListView
    val tomenu = findViewById<Button>(R.id.tomenu)
    tomenu.setOnClickListener{
        this.startActivity(Intent(this, MenuActivity::class.java))
    }
    fab.setOnClickListener{view ->
        //Show Dialog here to add new Item
        addNewItemDialog()

    }

        toDoItemList = mutableListOf<ToDoItem>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        var mAuth = FirebaseAuth.getInstance()
        val userId = mAuth!!.currentUser!!.uid
        mDatabase.child("Users").child(userId).orderByKey().addListenerForSingleValueEvent(itemListener)

    }
    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        //Check if current database contains any collection
        if (items.hasNext()) {
            val toDoListindex = items.next()
            val itemsIterator = toDoListindex.children.iterator()

            //check if the collection has any to do items or not
            while (itemsIterator.hasNext()) {
                //get current item
                val currentItem = itemsIterator.next()
                val todoItem = ToDoItem.create()
                //get current data in a map
                val map = currentItem.getValue() as HashMap<String, Any>
                //key will return Firebase ID
                todoItem.objectId = currentItem.key
                todoItem.done = map.get("done") as Boolean?
                todoItem.itemText = map.get("itemText") as String?
                toDoItemList!!.add(todoItem);
            }
        }
        //alert adapter that has changed
        adapter.notifyDataSetChanged()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)
        alert.setMessage("Введите название продукта и его срок годности")
        alert.setTitle("Добавляем новый продукт")
        val picker = DatePicker(this)
        picker.calendarViewShown = false
        val lila1 = LinearLayout(this)
        lila1.orientation = 1 //1 is for vertical orientation
        val input = EditText(this)
        val input1 = picker
        lila1.addView(input)
        lila1.addView(input1)
        alert.setView(lila1)
        alert.setView(lila1)
        alert.setPositiveButton("Submit") { dialog, positiveButton ->
            var todoItem = ToDoItem.create()
            todoItem.itemText = input.text.toString()
            todoItem.year = input1.year.toString()
            todoItem.month = input1.month.toString()
            todoItem.day = input1.dayOfMonth.toString()
            todoItem.done = true

            var mDatabase = FirebaseDatabase.getInstance().reference
            var mAuth = FirebaseAuth.getInstance()
            val userId = mAuth!!.currentUser!!.uid
            //We first make a push so that a new item is made with a unique ID
            val newItem = mDatabase.child("Users").child(userId).child("products").push()
            todoItem.objectId = newItem.key
            newItem.setValue(todoItem)
            dialog.dismiss()
            Toast.makeText(this, "Успешно добавлено ", Toast.LENGTH_SHORT).show()
            this.startActivity(Intent(this, ProductActivity::class.java))
        }
        alert.show()
    }

}

class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDoItem>) : BaseAdapter() {
    private var rowListener: ItemRowListener = context as ItemRowListener
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemText: String = itemList.get(position).itemText as String
        val objectId: String = itemList.get(position).objectId as String
        val view: View
        val vh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.rowitems, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.label.text = itemText
        vh.ibDeleteObject.setOnClickListener {
            rowListener.onItemDelete(objectId)

        }
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
        val ibDeleteObject: ImageButton = row!!.findViewById<ImageButton>(R.id.iv_cross) as ImageButton

    }
}