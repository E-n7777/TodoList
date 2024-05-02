package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView

class todoAdapter(private val data: List<Int>) :
    RecyclerView.Adapter<todoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val item: TextView = view.findViewById(R.id.tv_item)

        fun bind(){

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: todoAdapter.TodoViewHolder, position: Int) {
    holder.bind()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}