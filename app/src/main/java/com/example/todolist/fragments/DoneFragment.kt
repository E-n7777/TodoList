package com.example.todolist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.TodoItem
import com.example.todolist.DoneRvAdapter
import com.example.todolist.itemtouchhelpers.DoneSwipeDelete
import com.example.todolist.databinding.FragmentDoneBinding
import com.example.todolist.viewmodel.TaskViewModel

class DoneFragment : Fragment() {
    private lateinit var doneAdapter: DoneRvAdapter
    private lateinit var mViewModel: TaskViewModel
    private var mbinding: FragmentDoneBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        mViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
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

        initRv()

        val itemHelper = ItemTouchHelper(DoneSwipeDelete(mViewModel, doneAdapter))
        itemHelper.attachToRecyclerView(mbinding?.rvDone)

    }


    private fun initRv() {
        doneAdapter = DoneRvAdapter { todoItem: TodoItem ->
            mViewModel.checkboxChecked(todoItem)
        }
        mbinding?.rvDone?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doneAdapter
        }
        mViewModel.doneList.observe(viewLifecycleOwner) { doneList ->
            if (doneList.isEmpty()) {
                mbinding?.tvWarnDone?.visibility = View.VISIBLE
            } else {
                mbinding?.tvWarnDone?.visibility = View.GONE
            }

            doneAdapter.submitList(doneList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}