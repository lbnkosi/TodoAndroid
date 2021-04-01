package com.dstv.tododstv.features.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.databinding.FragmentSortBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.task.TaskCallback
import com.dstv.tododstv.features.todo.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSortTasks : BaseFragment() {

    private var callback: TaskCallback? = null

    private lateinit var binding: FragmentSortBinding

    private val viewModel: TodoViewModel by activityViewModels()

    companion object {
        fun newInstance(aCallback: TaskCallback) = FragmentSortTasks().apply {
            callback = aCallback
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSortBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.radioGroup.setOnCheckedChangeListener { _, _ -> callback?.onComplete() }
    }

}