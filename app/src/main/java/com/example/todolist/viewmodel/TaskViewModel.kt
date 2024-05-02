package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val mutableList = mutableListOf<String>()

    private val mutableLD: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    val todoList: LiveData<List<String>>
        get() = mutableLD
    fun getData(){


    }

}