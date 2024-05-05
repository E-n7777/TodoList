package com.example.todolist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.fragments.DoneFragment
import com.example.todolist.fragments.TodoFragment

class MainActivity : AppCompatActivity() {
    private val todoFragment = TodoFragment()
    private val doneFragment = DoneFragment()
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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

        val bottomNav = mBinding.bottomNavigationView

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, todoFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, doneFragment)
            .hide(doneFragment)
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.todoFragment -> {
                    supportFragmentManager.beginTransaction()
                        .manageFragment(item)

                    true
                }

                R.id.doneFragment -> {
                    supportFragmentManager.beginTransaction()
                        .manageFragment(item)

                    true
                }

                else -> false
            }
        }
    }

    fun FragmentTransaction.manageFragment(item: MenuItem): FragmentTransaction {
        if (todoFragment.isVisible && item.itemId == R.id.doneFragment) {
            supportFragmentManager.beginTransaction().hide(todoFragment)
                .show(doneFragment)
                .commit()
        } else if (doneFragment.isVisible && item.itemId == R.id.todoFragment) {
            supportFragmentManager.beginTransaction().hide(doneFragment)
                .show(todoFragment).commit()
        }
        return this
    }
}