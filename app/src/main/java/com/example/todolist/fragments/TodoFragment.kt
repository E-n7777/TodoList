package com.example.todolist.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentTodoBinding
import com.example.todolist.Adapters.TodoAdapter
import com.example.todolist.TodoItem
import com.example.todolist.viewmodel.TaskViewModel

class TodoFragment : Fragment() {
    private lateinit var mViewModel: TaskViewModel
    private lateinit var todoAdapter: TodoAdapter
    private var mbinding: FragmentTodoBinding? = null

    var taskList = mutableListOf<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        mViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentTodoBinding.inflate(inflater, container, false)

        return mbinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChange()
    }

    private fun initChange() {
        if (taskList.isEmpty()) {
            mbinding!!.tvWarnNull.visibility = View.VISIBLE
        } else {
            mbinding!!.tvWarnNull.visibility = View.GONE
            initRv()
        }

        mbinding?.imgAdd?.setOnClickListener {
            if (mbinding!!.etAdd.text.trim().isEmpty()) {
                requireContext().toast("您还没有输入任何待办事项哦")
            } else {
                val todoItem = TodoItem(mbinding!!.etAdd.text.toString())
                taskList.add(todoItem)
                mbinding!!.etAdd.text.clear()
                Log.d("todoItem.task", "initChange-->"+taskList)
                initRv()
            }
        }
    }

    private fun initRv() {
        todoAdapter=TodoAdapter(mViewModel.todoList.value ?: emptyList()) { todoItem ->
            mViewModel.checkboxChecked(todoItem)
        }
        mbinding!!.rvTodo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mbinding!!.rvTodo.adapter = todoAdapter
        }
        mViewModel.todoList.observe(requireActivity()){todoList->
            taskList= todoList.toMutableList()
            todoAdapter.updateList(todoList)
        }
        Log.d("taskList", "initRv-->"+mViewModel.todoList)
    }

    fun Context.toast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}