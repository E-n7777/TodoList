package com.example.todolist.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.TodoItem
import com.example.todolist.TodoRvAdapter
import com.example.todolist.TodoSwipeDelete
import com.example.todolist.databinding.FragmentTodoBinding
import com.example.todolist.viewmodel.TaskViewModel

class TodoFragment : Fragment() {

    private lateinit var mViewModel: TaskViewModel
    private lateinit var todoAdapter: TodoRvAdapter
    private var mbinding: FragmentTodoBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        mViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
        //mViewModel.init(requireActivity().application)
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

        initClick()
        initRv()

        val itemHelper = ItemTouchHelper(TodoSwipeDelete(mViewModel, todoAdapter))
        itemHelper.attachToRecyclerView(mbinding?.rvTodo)

    }

    private fun initClick() {
        var itemId: Int = 1
        mbinding?.imgAdd?.setOnClickListener {
            if (mbinding!!.etAdd.text.trim().isEmpty()) {
                requireContext().toast("您还没有输入任何待办事项哦")
            } else {

                val newItem = TodoItem(itemId++, mbinding!!.etAdd.text.toString())

                mViewModel.addTask(newItem)
                mbinding!!.etAdd.text.clear()


            }
        }
    }

    private fun initRv() {

        todoAdapter = TodoRvAdapter { todoItem: TodoItem ->
            mViewModel.checkboxChecked(todoItem)
        }

        mbinding!!.rvTodo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }

        mViewModel.todoList.observe(viewLifecycleOwner) { todoList ->
            if (todoList.isEmpty()) {
                mbinding!!.tvWarnNull.visibility = View.VISIBLE
            } else {
                mbinding!!.tvWarnNull.visibility = View.GONE
            }

            todoAdapter.submitList(todoList)

        }
    }

    /*fun getSavedList(){
        val sp: SharedPreferences = getSharedPreferences("self", Context.MODE_PRIVATE)

    }*/

    private fun Context.toast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}