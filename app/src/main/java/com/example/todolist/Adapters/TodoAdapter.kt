package com.example.todolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.viewmodel.TaskViewModel

class todoAdapter(private var taskList: List<String>, private val viewModel: TaskViewModel) :
    RecyclerView.Adapter<todoAdapter.TodoViewHolder>() {
    private var itemsList: List<String> = taskList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        viewModel.todoItems.observe(view.findViewTreeLifecycleOwner()!!) {
            itemsList = viewModel.todoItems.value!!
            notifyDataSetChanged()
        }
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val userInput = itemsList[position]
        holder.bind(userInput)
    }

    override fun getItemCount(): Int = itemsList.size

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_text: TextView = view.findViewById(R.id.tv_item)
        val item_check: CheckBox = view.findViewById(R.id.checkBox)
        fun bind(userInput: String) {
            item_text.text = userInput
            item_check.setOnCheckedChangeListener { buttonView, isChecked ->
                item_check.isChecked = viewModel.checkboxChecked(userInput, isChecked)
            }
        }

    }
}