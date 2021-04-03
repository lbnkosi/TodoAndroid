package com.dstv.tododstv.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.databinding.FragmentSettingsBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.task.TaskCallback
import com.dstv.tododstv.features.todo.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private lateinit var callback: TaskCallback

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: TodoViewModel by activityViewModels()

    companion object {
        fun newInstance(aCallback: TaskCallback) = SettingsFragment().apply {
            callback = aCallback
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.deleteAll.setOnClickListener {
            viewModel.deleteAllTasks()
            callback.onComplete()
        }
    }

}