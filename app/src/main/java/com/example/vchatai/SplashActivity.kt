package com.example.chatvai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.vchatai.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            Toast.makeText(this,"WELCOME", Toast.LENGTH_SHORT).show()
        },4000)
    }
}