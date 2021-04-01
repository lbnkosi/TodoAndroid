package com.dstv.tododstv.features.todo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dstv.tododstv.R
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.databinding.ActivityTodoBinding
import com.dstv.tododstv.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TodoActivity : BaseActivity() {

    private lateinit var binding: ActivityTodoBinding

    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo)
    }
}