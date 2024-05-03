package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class todoAdapter(private val taskList: List<String>) :
    RecyclerView.Adapter<todoAdapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: todoAdapter.TodoViewHolder, position: Int) {
        val userInput = taskList[position]
        holder.item.text = userInput
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: TextView = view.findViewById(R.id.tv_item)
    }
}