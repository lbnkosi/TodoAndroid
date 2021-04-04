package com.dstv.tododstv.features.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dstv.tododstv.databinding.FragmentTodoBinding
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.settings.SettingsFragment
import com.dstv.tododstv.features.sort.SortFragment
import com.dstv.tododstv.features.task.ManageTaskFragment
import com.dstv.tododstv.features.common.TaskCallback
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
        binding.sort.setOnClickListener { openBottomSheet(SortFragment.newInstance(this), SortFragment::class.java.name) }
        binding.settingsIcon.setOnClickListener { openBottomSheet(SettingsFragment.newInstance(this), SettingsFragment::class.java.name) }
        binding.fab.setOnClickListener { openBottomSheet(ManageTaskFragment.newInstance(false, null, this), ManageTaskFragment::class.java.name) }
    }

    private fun openBottomSheet(fragment: BaseFragment, tag: String) {
        openBottomSheet(fragment).show(childFragmentManager, tag)
    }

    override fun onComplete(refresh: Boolean) {
        if (refresh) viewModel.getTasks()
        dismissBottomSheet()
    }
}