package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var tvCount: TextView
    private lateinit var btnCount: Button
    private lateinit var btnToast: Button
    private lateinit var btnRandom: Button
    private var count = 0

    // Activity Result API 설정
    private val randomActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getIntExtra("random_number", 0)?.let { randomNumber ->
                count = randomNumber
                tvCount.text = count.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        tvCount = findViewById(R.id.tv_count)
        btnCount = findViewById(R.id.btn_count)
        btnToast = findViewById(R.id.btn_toast)
        btnRandom = findViewById(R.id.btn_random)
    }

    private fun setupListeners() {
        btnToast.setOnClickListener {
            Toast.makeText(this, "현재 카운트: $count", Toast.LENGTH_SHORT).show()
        }

        btnCount.setOnClickListener {
            count++
            tvCount.text = count.toString()
        }

        btnRandom.setOnClickListener {
            val intent = Intent(this, RandomActivity::class.java).apply {
                putExtra("max_count", count)
            }
            randomActivityLauncher.launch(intent)
        }
    }
}
/* //Version...
if (Build.VERSION.SDK_INT >= BUILD.VERSION_CODE.TIRAMISU){
    model = intent.getSerializableExtra("data", T::class.java)
} else{
    model = intent.getSerializableExtra("data") as T?
}*/ㄴㅇ