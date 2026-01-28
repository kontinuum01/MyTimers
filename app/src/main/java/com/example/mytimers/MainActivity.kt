package com.example.mytimers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytimers.coroutines.CoroutinesActivity
import com.example.mytimers.databinding.ActivityMainBinding
import com.example.mytimers.rxjava.RxJavaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rxJavaButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RxJavaActivity::class.java)
            startActivity(intent)
        }

        binding.coroutinesButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CoroutinesActivity::class.java)
            startActivity(intent)
        }
    }
}
