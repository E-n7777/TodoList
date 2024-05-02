package com.example.todolist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private val mBinding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(mBinding.root)

        sp = getSharedPreferences("self", Context.MODE_PRIVATE)
        initClick()
    }


    private fun initClick() {
        val clickListener = View.OnClickListener { v ->
            when (v) {
                mBinding.btnLoginSignIn -> {
                    val etLoginPassword = mBinding.etLoginPassword.text.toString()
                    val etLoginName = mBinding.etLoginName.text.toString()
                    val savedPassword = sp.getString(etLoginName, "").toString()
                    if (sp.contains(etLoginName) && etLoginPassword == savedPassword) {
                        val intent = Intent(this, MainActivity::class.java)
                        Toast.makeText(this, "欢迎登陆~", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show()
                    }
                }

                mBinding.btnLoginSignUp -> {
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        mBinding.btnLoginSignIn.setOnClickListener(clickListener)
        mBinding.btnLoginSignUp.setOnClickListener(clickListener)
    }
}