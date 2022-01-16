package com.lbnkosi.todoapp.features.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.lbnkosi.todoapp.databinding.FragmentSortBinding
import com.lbnkosi.todoapp.features.common.BaseFragment
import com.lbnkosi.todoapp.features.common.TaskCallback
import com.lbnkosi.todoapp.features.todo.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SortFragment : BaseFragment() {

    private lateinit var callback: TaskCallback

    private lateinit var binding: FragmentSortBinding

    private val viewModel: TodoViewModel by activityViewModels()

    companion object {
        fun newInstance(aCallback: TaskCallback) = SortFragment().apply {
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
        binding.radioGroup.setOnCheckedChangeListener { _, _ -> callback.onComplete(false) }
    }

}