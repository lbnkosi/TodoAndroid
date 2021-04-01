package com.dstv.tododstv.features.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.databinding.FragmentTodoBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.task.AddTaskFragment
import com.dstv.tododstv.features.task.TaskCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment: BaseFragment(), TaskCallback {

    private lateinit var binding: FragmentTodoBinding

    private val viewModel : TodoViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTodoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.fab.setOnClickListener { getBottomSheet(AddTaskFragment.newInstance(false, null, this))?.show(parentFragmentManager, "AddTask") }
    }

    override fun onComplete() {
        dismissBottomSheet()
    }
}