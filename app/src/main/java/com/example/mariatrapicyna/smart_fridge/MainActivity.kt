package com.example.mariatrapicyna.smart_fridge

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase.getInstance

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val singIn = findViewById<Button>(R.id.singIn)
        val register = findViewById<Button>(R.id.register)

        var mProgressBar = ProgressDialog(this)
        var mAuth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        }
        singIn.setOnClickListener {
            val Email = email.text.toString()
            val Password = password?.text.toString()
            if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password)) {
                mProgressBar!!.setMessage("Подождите, мы входим :)...")
                mProgressBar!!.show()
                mAuth!!.signInWithEmailAndPassword(Email!!, Password!!)
                    .addOnCompleteListener(this) { task ->
                        mProgressBar!!.hide()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with signed-in user's information
                            this.startActivity(Intent(this, MenuActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this@MainActivity, "Не удалось войти. Проверьте данные",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Введите все данные", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun isSinged(): Boolean {
        /*
        * Проверяет корректность данных
        * Допишете, когда доделаете авторизацию :)  */

        return true
    }
}