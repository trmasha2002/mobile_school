package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)


        val email = rootView.findViewById<TextView>(R.id.email)
        val username = rootView.findViewById<TextView>(R.id.username)
        val oldPassword = rootView.findViewById<EditText>(R.id.oldPassword)
        val firstNewPassword = rootView.findViewById<EditText>(R.id.firstNewPassword)
        val secondNewPassword = rootView.findViewById<EditText>(R.id.secondNewPassword)
        val changePassword = rootView.findViewById<Button>(R.id.changePassword)

        changePassword.setOnClickListener {
            if (firstNewPassword.text == secondNewPassword.text && firstNewPassword.text != oldPassword.text) {
                //Тут что-то происходит :)
            } else if (firstNewPassword.text != secondNewPassword.text) {
                Toast.makeText(activity, "Passwords do not match", Toast.LENGTH_LONG).show()
            } else if (firstNewPassword.text == secondNewPassword.text && firstNewPassword.text == oldPassword.text) {
                Toast.makeText(activity, "The new password is the same as the previous one.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Dunn's not correct", Toast.LENGTH_LONG).show()
            }
        }
        return rootView
    }
}