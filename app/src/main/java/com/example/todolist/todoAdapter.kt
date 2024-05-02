package com.example.todolist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class todoAdapter( private val data: List<Int>)
    :RecyclerView.Adapter<todoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(view: View):RecyclerView.ViewHolder(view) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoAdapter.TodoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: todoAdapter.TodoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}