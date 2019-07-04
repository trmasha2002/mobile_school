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
import java.util.regex.Matcher
import java.util.regex.Pattern

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

            if (isValidMail(email.text.toString())) {
                if (isValidPassword(firstPassword.text.toString()) && isValidPassword(secondPassword.text.toString())) {
                    if (firstPassword.text.toString() == secondPassword.text.toString()) {
                        if (username.text.toString().isNotEmpty()) {
                            mDatabase = FirebaseDatabase.getInstance()
                            mDatabaseReference = mDatabase!!.reference!!.child("Users")
                            mAuth = FirebaseAuth.getInstance()
                            println("Ye")
                            mProgressBar = ProgressDialog(this)
                            mProgressBar!!.setMessage("Registering User...")
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
                                        this.startActivity(Intent(this, MainActivity::class.java))
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                        Toast.makeText(
                                            this@RegisterActivity, "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(applicationContext, "Check username", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        println(firstPassword.text)
                        println("dfsf")
                        println(secondPassword.text)
                        Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Check password", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Check mail", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun isValidMail(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^+\\'=!:;(){}\"])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()

    }
}