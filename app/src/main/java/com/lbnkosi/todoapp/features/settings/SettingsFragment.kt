package com.lbnkosi.todoapp.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.lbnkosi.todoapp.databinding.FragmentSettingsBinding
import com.lbnkosi.todoapp.features.common.BaseFragment
import com.lbnkosi.todoapp.features.common.TaskCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private lateinit var callback: TaskCallback

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: SettingsViewModel by activityViewModels()

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
            callback.onComplete(true)
        }
    }
}