package com.example.todolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.TodoItem

class TodoAdapter(
    private var taskList: List<TodoItem>,
    private val onTodoClickListener: (TodoItem) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val userInput = taskList[position]
        holder.bind(userInput)
    }

    override fun getItemCount(): Int = taskList.size
    fun updateList(newList: List<TodoItem>){
        taskList = newList
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_text: TextView = view.findViewById(R.id.tv_item)
        val item_check: CheckBox = view.findViewById(R.id.checkBox)
        val item: View = view.findViewById(R.id.layout_item_list)
        fun bind(userInput: TodoItem) {
            item_text.text = userInput.task
            item_check.isChecked = userInput.isCompleted
            item.setOnClickListener { onTodoClickListener(userInput) }
        }

    }
}