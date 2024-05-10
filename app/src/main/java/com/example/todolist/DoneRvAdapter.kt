package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DoneRvAdapter(
    private val onDoneClickListener: (TodoItem) -> Unit
) : ListAdapter<TodoItem, DoneRvAdapter.ViewHolder>(object : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.task == newItem.task
    }

}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneRvAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: DoneRvAdapter.ViewHolder, position: Int) {
        val taskItem = getItem(position)
        holder.bind(taskItem)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_text: TextView = view.findViewById(R.id.tv_item)
        val item_check: CheckBox = view.findViewById(R.id.checkBox)

        fun bind(userDone: TodoItem) {
            item_text.text = userDone.task
            item_check.isChecked = userDone.isCompleted
            item_check.setOnClickListener {
                onDoneClickListener(userDone)

            }

        }
    }

}