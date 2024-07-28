package com.example.ap3.login

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ap3.R

class Home: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val imageView: ImageView = findViewById(R.id.image_view)
        val heloTextView: TextView = findViewById(R.id.helo)
        val pleaseTextView: TextView = findViewById(R.id.please)
        val btnSignup: TextView = findViewById(R.id.btnSignup)
        val btnLogin: TextView = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            val intent = Intent(this, Registrasi::class.java)
            startActivity(intent)
        }
    }
}
