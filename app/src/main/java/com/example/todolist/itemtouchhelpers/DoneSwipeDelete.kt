package com.example.todolist.itemtouchhelpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.DoneRvAdapter
import com.example.todolist.viewmodel.TaskViewModel

class DoneSwipeDelete(
    private val viewModel: TaskViewModel,
    private val doneAdapter: DoneRvAdapter
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
        viewModel.deleteDoneItem(position)

        doneAdapter.notifyItemRemoved(viewHolder.adapterPosition)
        doneAdapter.notifyItemRangeChanged(viewHolder.adapterPosition, doneAdapter.itemCount)
    }
}