package com.lbnkosi.todoapp.features.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.lbnkosi.todoapp.databinding.FragmentTodoBinding
import com.lbnkosi.todoapp.features.common.BaseFragment
import com.lbnkosi.todoapp.features.settings.SettingsFragment
import com.lbnkosi.todoapp.features.sort.SortFragment
import com.lbnkosi.todoapp.features.task.ManageTaskFragment
import com.lbnkosi.todoapp.features.common.TaskCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : BaseFragment(), TaskCallback {

    private lateinit var binding: FragmentTodoBinding

    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTodoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.show = false
        configureOnClicks()
    }

    private fun configureOnClicks() {
        binding.showHideStats.setOnClickListener { binding.show = !binding.show!! }
        binding.sort.setOnClickListener { show(SortFragment.newInstance(this)) }
        binding.settingsIcon.setOnClickListener { show(SettingsFragment.newInstance(this)) }
        binding.fab.setOnClickListener { show(ManageTaskFragment.newInstance(false, null, this)) }
    }

    override fun onComplete(refresh: Boolean) {
        if (refresh) viewModel.getTasks()
        dismissBottomSheet()
    }
}