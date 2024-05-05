package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.todolist.viewmodel.TaskViewModel

class TodoRvAdapter(
    private val onTodoClickListener: (TodoItem) -> Unit
) : ListAdapter<TodoItem, TodoRvAdapter.FirstViewHolder>(
    object : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.task == newItem.task && oldItem.isCompleted == newItem.isCompleted
        }

    },
) {
    inner class FirstViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val item_text: TextView = view.findViewById(R.id.tv_item)
        private val item_check: CheckBox = view.findViewById(R.id.checkBox)

        fun bind(userInput: TodoItem) {
            item_text.text = userInput.task
            item_check.isChecked = userInput.isCompleted

            item_check.setOnClickListener {
                onTodoClickListener(userInput)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FirstViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        val taskItem = getItem(position)
        holder.bind(taskItem)
    }
}