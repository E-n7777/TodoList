package com.example.todolist.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.TodoItem
import com.google.gson.Gson

class TaskViewModel() : ViewModel() {
   /* private lateinit var application: Application

    private val sp = application.getSharedPreferences("self", Context.MODE_PRIVATE)
    private val word = sp.getString(sp.getString("username", ""), "")*/
   /* private val todoItems =
        Gson().fromJson(sp.getString(word, ""), Array<TodoItem>::class.java).toList()*/

    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    private val _doneList = MutableLiveData<List<TodoItem>>()
    val doneList: LiveData<List<TodoItem>> get() = _doneList

    /*fun init(application: Application){
        this.application=application
    }*/

    init {

        // 初始化待办事项列表和已完成列表
        _todoList.value = emptyList()
        _doneList.value = emptyList()
    }

    fun addTask(newItem: TodoItem) {
        // 获取当前 List 的值，如果为 null 则创建一个新的空列表
        val currentList = _todoList.value ?: emptyList()

        // 添加新项
        val updatedList = currentList + newItem

        // 更新 MutableLiveData 对象
        _todoList.value = updatedList

        //更新数据库
        //sp.edit().clear().putString(word, Gson().toJson(updatedList)).apply()

    }

    fun deleteTodoItem(position: Int) {

        val currentList = _todoList.value?.toMutableList()

        currentList?.removeAt(position)

        _todoList.value = currentList?.toList()

        //sp.edit().clear().putString(word, Gson().toJson(currentList)).apply()

    }

    fun deleteDoneItem(position: Int) {
        val currentList = _doneList.value?.toMutableList()
        currentList?.removeAt(position)
        _doneList.value = currentList!!
    }

    fun checkboxChecked(todoItem: TodoItem) {

        todoItem.isCompleted = !todoItem.isCompleted

        if (todoItem.isCompleted) {
            // 从 todoList 中移除 todoItem
            val currentTodoList = _todoList.value ?: emptyList()
            val updatedTodoList = currentTodoList - todoItem
            _todoList.value = updatedTodoList

            // 添加 todoItem 到 doneList
            val currentDoneList = _doneList.value ?: emptyList()
            val updatedDoneList = currentDoneList + todoItem
            _doneList.value = updatedDoneList
        } else {
            // 从 doneList 中移除 todoItem
            val currentDoneList = _doneList.value ?: emptyList()
            val updatedDoneList = currentDoneList - todoItem
            _doneList.value = updatedDoneList

            // 添加 todoItem 到 todoList
            val currentTodoList = _todoList.value ?: emptyList()
            val updatedTodoList = currentTodoList + todoItem
            _todoList.value = updatedTodoList
        }

    }
}