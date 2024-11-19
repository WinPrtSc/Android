package com.example.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.widget.TextView


class RandomActivity : AppCompatActivity() {
    private lateinit var tvRandom: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvRandom = findViewById(R.id.tv_random)

        val maxCount = intent.getIntExtra("max_count", 0)
        val randomNumber = (0..maxCount).random()

        tvRandom.text = randomNumber.toString()

        // 뒤로 가기 버튼 눌렀을 때 결과 전달
        setResult(RESULT_OK, Intent().putExtra("random_number", randomNumber))
    }
}