package com.lbnkosi.todoapp.features.todo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lbnkosi.todoapp.R
import com.lbnkosi.todoapp.databinding.ActivityTodoBinding
import com.lbnkosi.todoapp.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TodoActivity : BaseActivity() {

    private lateinit var binding: ActivityTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo)
    }
}