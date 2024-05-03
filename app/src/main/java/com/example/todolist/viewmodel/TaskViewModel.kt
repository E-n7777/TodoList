package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var taskList: MutableList<String> = mutableListOf()
    var doneList: MutableList<String> = mutableListOf()
}