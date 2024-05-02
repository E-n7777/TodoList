package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val homeLD: LiveData<String> get() = mutableLD
    private val mutableLD = MutableLiveData<String>()

    fun getData(){


       // mutableLD.value=
    }

}