package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_recipes.*


class RecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    fun onClick(view: View) {
        val ref = FirebaseDatabase.getInstance().reference.child("recipes")
        val bookListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val url = dataSnapshot.child("1").child("Рецепт Фото").getValue(String::class.java)
                print(url);
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //log error here and handle it somehow
            }
        }
        //вешаем наш слушатель на нашу ссылку
        ref.addValueEventListener(bookListener)
    }


}