package com.example.todolist

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.viewmodel.TaskViewModel

class TodoSwipeDelete(
    private val viewModel: TaskViewModel,
    private val todoAdapter: TodoRvAdapter
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        viewModel.deleteTodoItem(position)

        todoAdapter.notifyItemRemoved(viewHolder.adapterPosition)
        todoAdapter.notifyItemRangeChanged(viewHolder.adapterPosition, todoAdapter.itemCount)
    }
}