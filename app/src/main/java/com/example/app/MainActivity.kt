package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.app.R

class MainActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃 선택 (LinearLayout, RelativeLayout, ConstraintLayout 중 하나 선택)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        emailEditText = findViewById(R.id.et_login_email)
        passwordEditText = findViewById(R.id.et_login_password)
        loginButton = findViewById(R.id.btn_login)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            when {
                email.isEmpty() -> {
                    emailEditText.error = "이메일을 입력해주세요"
                    emailEditText.requestFocus()
                }
                !isValidEmail(email) -> {
                    emailEditText.error = "올바른 이메일 형식이 아닙니다"
                    emailEditText.requestFocus()
                }
                password.isEmpty() -> {
                    passwordEditText.error = "비밀번호를 입력해주세요"
                    passwordEditText.requestFocus()
                }
                password.length < 6 -> {
                    passwordEditText.error = "비밀번호는 6자리 이상이어야 합니다"
                    passwordEditText.requestFocus()
                }
                else -> {
                    // TODO: 실제 로그인 로직 구현
                    performLogin(email, password)
                }
            }
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun performLogin(email: String, password: String) {
        // 실제 로그인 로직을 구현할 수 있습니다.
        // 예: API 호출, 데이터베이스 확인 등

        // 임시 성공 메시지
        Toast.makeText(
            this,
            "로그인 시도: $email",
            Toast.LENGTH_SHORT
        ).show()
    }
}