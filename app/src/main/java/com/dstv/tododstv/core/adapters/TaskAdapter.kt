package com.dstv.tododstv.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dstv.tododstv.R.drawable.blue_button_10dp
import com.dstv.tododstv.R.drawable.red_button_10dp
import com.dstv.tododstv.core.mappers.display.TaskDisplayMapper
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.core.util.DataBoundListAdapter
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import com.dstv.tododstv.databinding.TaskRowBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.task.AddTaskFragment
import com.dstv.tododstv.features.common.TaskCallback
import com.dstv.tododstv.features.todo.TodoViewModel
import dagger.hilt.android.internal.managers.FragmentComponentManager

/**
 *  NB
 *  Find a better way to implement multi selection without holding a reference to a viewmodel
 *  It's bad practice for adapter to be lifecycle aware and observing changes to data. Only UI controllers such as activities and fragments are
 *  allowed to hold viewmodel references
 *
 *  The reason I went with this option was time. I only had roughly 2 days to do this which wasn't enough to do proper R&D
 */
class TaskAdapter(private val fragmentManager: FragmentManager) : DataBoundListAdapter<Task, TaskRowBinding>(), TaskCallback {

    private lateinit var viewModel: TodoViewModel

    private var bottomSheet: BottomSheetDialogUtilFragment? = null

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = newItem == oldItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = newItem.title == oldItem.title

    override fun createBinding(parent: ViewGroup?) = TaskRowBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

    override fun bind(binding: TaskRowBinding, item: Task) {
        binding.taskDisplay = TaskDisplayMapper().toTaskDisplay(item)
        binding.root.setOnLongClickListener { onViewLongClick(binding, item) }
        binding.root.setOnClickListener { onClick(binding, item) }
        isSelectAllEnabled(binding, item)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val context = (FragmentComponentManager.findActivity(recyclerView.context) as FragmentActivity)
        viewModel = ViewModelProvider(context).get(TodoViewModel::class.java)
    }

    private fun onClick(binding: TaskRowBinding, item: Task) {
        binding.root.apply {
            if (viewModel.getMultiSelectIsEnabled()) when {
                tag == SELECTED -> setBackground(binding, BLUE_BG, item)
                tag != SELECTED -> setBackground(binding, RED_BG, item)
            } else openEditTask(item)
        }
    }

    private fun onViewLongClick(binding: TaskRowBinding, item: Task): Boolean {
        if (binding.root.tag != SELECTED) {
            setBackground(binding, RED_BG, item)
            viewModel.setMultiSelect(true)
        }
        return true
    }

    private fun setBackground(binding: TaskRowBinding, type: String, item: Task) {
        val isSelected = type == RED_BG
        binding.root.apply {
            background = (if (isSelected) getDrawable(context, red_button_10dp) else getDrawable(context, blue_button_10dp))
            if (isSelected) addToSelectedList(item) else removeFromSelectedList(item)
            tag = (if (isSelected) SELECTED else DESELECTED)
        }
    }

    private fun addToSelectedList(item: Task) {
        viewModel.setMultiSelectTaskList(item)
    }

    private fun removeFromSelectedList(item: Task) {
        viewModel.removeFromSelectedList(item)
    }

    private fun isSelectAllEnabled(binding: TaskRowBinding, item: Task) {
        val activity = (FragmentComponentManager.findActivity(binding.root.context) as FragmentActivity)
        viewModel.selectAll.observe(activity, {
            if (it) setBackground(binding, RED_BG, item)
            else setBackground(binding, BLUE_BG, item)
        })
    }

    private fun openEditTask(item: Task) {
        getBottomSheet(AddTaskFragment.newInstance(true, item, this)).show(fragmentManager, AddTaskFragment::class.java.name)
    }

    private fun getBottomSheet(aFragment: BaseFragment): BottomSheetDialogUtilFragment {
        bottomSheet = BottomSheetDialogUtilFragment.newInstance(aFragment)
        return bottomSheet!!
    }

    private fun dismissBottomSheetDialog() {
        viewModel.getTasks()
        bottomSheet?.dismiss()
    }

    override fun onComplete(refresh : Boolean) {
        dismissBottomSheetDialog()
    }

    companion object {
        const val SELECTED = "Selected"
        const val DESELECTED = "Not_Selected"
        const val RED_BG = "red"
        const val BLUE_BG = "blue"
    }
}