package com.dstv.tododstv.features.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.databinding.FragmentManageTaskBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.common.TaskCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageTaskFragment : BaseFragment() {

    private lateinit var callback: TaskCallback

    private lateinit var binding: FragmentManageTaskBinding

    private val viewModel: ManageTaskViewModel by activityViewModels()

    companion object {

        private const val IS_EDIT = "IS_EDIT"

        private const val TASK = "TASK"

        fun newInstance(isEdit: Boolean = false, task: Task? = Task(), aCallback: TaskCallback) = ManageTaskFragment().apply {
            callback = aCallback
            arguments = Bundle().apply {
                putBoolean(IS_EDIT, isEdit)
                putParcelable(TASK, task)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentManageTaskBinding.inflate(inflater)
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