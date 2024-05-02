package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val mBinding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(mBinding.root)
        initClick()
    }

    private fun initClick() {
        val clickListener = View.OnClickListener { v ->
            when (v) {
                mBinding.tvSignUpBack -> {
                    finish()
                    Toast.makeText(this, "请登录~", Toast.LENGTH_SHORT).show()
                }

                mBinding.btnSignUp -> {
                    val etName = mBinding.etSignUpName.text.toString().trim()
                    val etPass = mBinding.etSignUpPassword.text.toString().trim()
                    val etRePass = mBinding.etSignUpRepassword.text.toString().trim()
                    val sp: SharedPreferences = getSharedPreferences("self", Context.MODE_PRIVATE)
                    if (etName.isEmpty() && etPass.isEmpty() && etRePass.isEmpty()) {
                        Toast.makeText(this, "请填写完整信息！", Toast.LENGTH_SHORT).show()
                    } else if (sp.contains(etName)) {
                        Toast.makeText(this, "该用户名已被注册", Toast.LENGTH_SHORT).show()
                    } else if (etPass != etRePass) {
                        Toast.makeText(this, "两次输入的密码不一样哦", Toast.LENGTH_SHORT).show()
                    } else {
                        val editor = sp.edit()
                        editor.putString(etName, etPass)
                        editor.apply()
                        Toast.makeText(this, "恭喜您，注册成功！", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
        mBinding.tvSignUpBack.setOnClickListener(clickListener)
        mBinding.btnSignUp.setOnClickListener(clickListener)
    }
}


