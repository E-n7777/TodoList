package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var taskList: MutableList<String> = mutableListOf()
    var doneList: MutableList<String> = mutableListOf()

    private val _todoItems = MutableLiveData<List<String>>()
    val todoItems: LiveData<List<String>> get() = _todoItems

    fun checkboxChecked(item: String, isCheck: Boolean) :Boolean{

        val currentList = _todoItems.value?.toMutableList() ?: mutableListOf()
        if (isCheck) {
            currentList.remove(item)
            _todoItems.value = currentList
            taskList = currentList
            return true
        }
        return false

    }
}