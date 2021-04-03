package com.dstv.tododstv.features.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.databinding.FragmentAddNoteBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.common.TaskCallback
import com.dstv.tododstv.features.todo.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : BaseFragment() {

    private lateinit var callback: TaskCallback

    private lateinit var binding: FragmentAddNoteBinding

    private val viewModel: CreateTaskViewModel by activityViewModels()

    companion object {

        private const val IS_EDIT = "IS_EDIT"

        private const val TASK = "TASK"

        fun newInstance(isEdit: Boolean = false, task: Task? = Task(), aCallback: TaskCallback) = AddTaskFragment().apply {
            callback = aCallback
            arguments = Bundle().apply {
                putBoolean(IS_EDIT, isEdit)
                putParcelable(TASK, task)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        fetchArguments()
        onComplete()
    }

    private fun fetchArguments() {
        arguments?.let { aBundle ->
            viewModel.setATask(aBundle.getParcelable(TASK))
            viewModel.setIsEdit(aBundle.getBoolean(IS_EDIT))
        }
    }

    private fun onComplete() {
        viewModel.success.observe(viewLifecycleOwner, {
            if (it) {
                callback.onComplete()
            }
        })
    }
}