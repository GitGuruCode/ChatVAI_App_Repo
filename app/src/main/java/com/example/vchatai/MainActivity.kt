package com.example.chatvai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatvai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chatbtn.setOnClickListener{
            startActivity(Intent(this,chatActivity::class.java))
        }
        binding.imagebtn.setOnClickListener{
            startActivity(Intent(this,ImageActivity::class.java))
        }


    }
}