package com.example.todolist.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentTodoBinding
import com.example.todolist.todoAdapter
import com.example.todolist.viewmodel.TaskViewModel

class TodoFragment : Fragment() {
    private lateinit var mViewModel: TaskViewModel

    private var mbinding: FragmentTodoBinding? = null
    val todoList = mutableListOf<String>()

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
        mViewModel.taskList.addAll(todoList)
    }

    private fun initChange() {
        if (todoList.isEmpty()) {
            mbinding!!.tvWarnNull.visibility = View.VISIBLE
        } else {
            mbinding!!.tvWarnNull.visibility = View.GONE
            initRv()
        }

        mbinding?.imgAdd?.setOnClickListener {
            if (mbinding!!.etAdd.text.trim().isEmpty()) {
                requireContext().toast("您还没有输入任何待办事项哦")
            } else {
                val addTask = mbinding!!.etAdd.text.toString()
                mbinding!!.etAdd.text.clear()

                todoList.add(addTask)
                initRv()
            }
        }
    }

    fun initRv() {
        val todoAdapter = todoAdapter(todoList)
        mbinding!!.rvTodo.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            mbinding!!.rvTodo.adapter = todoAdapter
        }
        todoAdapter.notifyDataSetChanged()
    }

    fun Context.toast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}