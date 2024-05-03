package com.example.todolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class doneAdapter(private val taskList: List<String>) :
    RecyclerView.Adapter<doneAdapter.DoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return DoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        val userInput = taskList[position]
        holder.item_text.text = userInput
        holder.item_check.isChecked = true
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class DoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_text: TextView = view.findViewById(R.id.tv_item)
        val item_check: CheckBox = view.findViewById(R.id.checkBox)
    }
}