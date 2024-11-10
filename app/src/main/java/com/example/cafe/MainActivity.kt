package com.example.cafe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var makeOrderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "OnCreate")

        initView()
        makeOrderButton.setOnClickListener {
            val userName: String = editTextName.text.toString().trim()
            val password: String = editTextPassword.text.toString().trim()

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    R.string.error_fields_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                launchNextScreen(userName)
            }

        }
    }

    private fun launchNextScreen(userName: String) {
        val intent = MakeOrderActivity.newIntent(this, userName)
        startActivity(intent)
    }

    private fun initView() {
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        makeOrderButton = findViewById(R.id.buttonSignIn)
    }
}