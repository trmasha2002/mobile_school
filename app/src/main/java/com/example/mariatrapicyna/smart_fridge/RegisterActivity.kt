package com.example.mariatrapicyna.smart_fridge

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.email)
        val firstPassword = findViewById<EditText>(R.id.firstPassword)
        val secondPassword = findViewById<EditText>(R.id.secondPassword)
        val username = findViewById<EditText>(R.id.username)
        val register = findViewById<Button>(R.id.register)
        var mDatabaseReference: DatabaseReference? = null
        var mDatabase: FirebaseDatabase? = null
        var mAuth: FirebaseAuth? = null
        var mProgressBar: ProgressDialog? = null
        val TAG = "CreateAccountActivity"


        register.setOnClickListener {
            if (firstPassword.text.toString().length < 6) {
                Toast.makeText(applicationContext, "Пароль должен быть не менее 6 символов", Toast.LENGTH_LONG).show()
            }
            if (firstPassword.text.toString()== secondPassword.text.toString()) {
                mDatabase = FirebaseDatabase.getInstance()
                mDatabaseReference = mDatabase!!.reference!!.child("Users")
                mAuth = FirebaseAuth.getInstance()
                println("Ye")
                mProgressBar = ProgressDialog(this)
                mProgressBar!!.setMessage("Регистрируем...")
                mProgressBar!!.show()
                var Username = username.text.toString()
                var Email = email.text.toString()
                var Password = firstPassword.text.toString()
                mAuth!!.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this) { task ->
                        mProgressBar!!.hide()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val userId = mAuth!!.currentUser!!.uid
                            //update user profile information
                            val currentUserDb = mDatabaseReference!!.child(userId)
                            currentUserDb.child("usertName").setValue(Username)
                            this.startActivity(Intent(this, MenuActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this@RegisterActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }}

                } else {
                println(firstPassword.text)
                println("dfsf")
                println(secondPassword.text)
                Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            }
        }
    }
}
