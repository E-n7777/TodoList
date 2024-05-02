package com.example.todolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.fragments.DoneFragment
import com.example.todolist.fragments.TodoFragment
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(mBinding.root)
        initMenu()
    }

    private fun initMenu() {
        val todoFragment = TodoFragment()
        val doneFragment = DoneFragment()

        val bottomNav = mBinding.bottomNavigationView
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.todoFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, todoFragment)
                        .commit()
                    true
                }
                R.id.doneFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, doneFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}