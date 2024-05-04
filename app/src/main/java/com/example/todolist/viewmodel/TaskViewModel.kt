package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.TodoItem

class TaskViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    private val _doneList = MutableLiveData<List<TodoItem>>()
    val doneList: LiveData<List<TodoItem>> get() = _doneList


    init {
        // 初始化待办事项列表和已完成列表
        _todoList.value = emptyList()
        _doneList.value = emptyList()
    }

    fun checkboxChecked(todoItem: TodoItem) {

        todoItem.isCompleted = !todoItem.isCompleted
        if (todoItem.isCompleted) {
            _doneList.value = _doneList.value?.plus(todoItem)
            _todoList.value = _todoList.value?.filter { it != todoItem }
        }else{
            _todoList.value = _todoList.value?.plus(todoItem)
            _doneList.value = _doneList.value?.filter { it != todoItem }
        }


    }
}