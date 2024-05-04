package com.example.todolist.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.Adapters.DoneAdapter
import com.example.todolist.TodoItem
import com.example.todolist.databinding.FragmentDoneBinding
import com.example.todolist.viewmodel.TaskViewModel

class DoneFragment : Fragment() {
    private lateinit var doneAdapter: DoneAdapter
    private lateinit var mViewModel: TaskViewModel
    private var mbinding: FragmentDoneBinding? = null

    var taskList = mutableListOf<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        mViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentDoneBinding.inflate(inflater, container, false)
        return mbinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChange()
    }

    private fun initChange() {
        if (taskList.isEmpty()) {
            mbinding!!.tvWarnDone.visibility = View.VISIBLE
        } else {
            mbinding!!.tvWarnDone.visibility = View.GONE
            initRv()
        }
        Log.d("doneList", "initChange-->" + mViewModel.doneList)
    }

    private fun initRv() {
        val doneAdapter = DoneAdapter(mViewModel.doneList.value ?: emptyList()) { todoItem ->
            mViewModel.checkboxChecked(todoItem)
        }
        mbinding?.rvDone?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mbinding!!.rvDone.adapter = doneAdapter
        }
        mViewModel.doneList.observe(requireActivity()) { doneList ->
            taskList = doneList.toMutableList()
            doneAdapter.updateList(doneList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}