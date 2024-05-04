package com.example.todolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.TodoItem

class DoneAdapter(
    private var taskList: List<TodoItem>,
    private val onDoneClickListener: (TodoItem) -> Unit
) :
    RecyclerView.Adapter<DoneAdapter.DoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return DoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        val userInput = taskList[position]
        holder.bind(userInput)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateList(doneList: List<TodoItem>) {
        taskList = doneList
        notifyDataSetChanged()
    }

    inner class DoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_text: TextView = view.findViewById(R.id.tv_item)
        val item_check: CheckBox = view.findViewById(R.id.checkBox)
        val item: View = view.findViewById(R.id.layout_item_list)
        fun bind(userDone: TodoItem) {
            item_text.text = userDone.task
            item_check.isChecked = userDone.isCompleted
            item.setOnClickListener { onDoneClickListener(userDone) }
        }


    }
}