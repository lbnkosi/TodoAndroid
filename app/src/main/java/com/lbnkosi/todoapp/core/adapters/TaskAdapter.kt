package com.lbnkosi.todoapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.lbnkosi.todoapp.core.enums.MultiSelectEnum
import com.lbnkosi.todoapp.core.enums.MultiSelectEnum.*
import com.lbnkosi.todoapp.core.extensions.*
import com.lbnkosi.todoapp.core.mappers.display.TaskDisplayMapper
import com.lbnkosi.todoapp.core.models.Task
import com.lbnkosi.todoapp.core.util.recyclerview.DataBoundListAdapter
import com.lbnkosi.todoapp.databinding.TaskRowBinding
import com.lbnkosi.todoapp.features.common.TaskCallback
import com.lbnkosi.todoapp.features.todo.TodoViewModel

class TaskAdapter(private val fragmentManager: FragmentManager) : DataBoundListAdapter<Task, TaskRowBinding>(), TaskCallback {

    private lateinit var viewModel: TodoViewModel

    override fun createBinding(parent: ViewGroup?) = TaskRowBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

    override fun bind(binding: TaskRowBinding, item: Task) {
        binding.taskDisplay = TaskDisplayMapper().toTaskDisplay(item)
        binding.root.setOnLongClickListener { binding.longClick(item) }
        binding.root.setOnClickListener { binding.click(item) }
        binding.isSelectAllEnabled(item)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel = getViewModel(recyclerView.context) as TodoViewModel
    }

    private fun TaskRowBinding.click(item: Task) = when {
        !viewModel.multiSelectEnabled -> showBottomSheet(getManageFragment(item, this@TaskAdapter), fragmentManager)
        else -> this.setBackground(status!!.getStatus(), item)
    }

    private fun TaskRowBinding.longClick(item: Task): Boolean {
        if (status != SELECTED) {
            this.setBackground(SELECTED, item)
            viewModel.multiSelectEnabled = true
        }
        return true
    }

    private fun TaskRowBinding.setBackground(status: MultiSelectEnum, item: Task) {
        root.background = status.isSelected().setBackground(root.context)
        viewModel.addRemoveFromDeleteList(status.isSelected(), item)
        this.status = status.isSelected().setStatus()
    }

    private fun TaskRowBinding.isSelectAllEnabled(item: Task) {
        viewModel.selectAll.observe(getActivity(root.context), {
            setBackground(if (it) SELECTED else NOT_SELECTED, item)
        })
    }

    override fun onComplete(refresh: Boolean) {
        viewModel.getTasks()
        dismissBottomSheetDialog()
    }

}