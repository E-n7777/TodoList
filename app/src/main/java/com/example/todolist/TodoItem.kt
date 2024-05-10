package com.example.todolist

data class TodoItem(val id: Int, val task: String, var isCompleted: Boolean = false)
