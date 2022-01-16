package com.lbnkosi.todoapp.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbnkosi.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val useCase: TaskUseCase) : ViewModel() {

    fun deleteAllTasks() {
        viewModelScope.launch {
            useCase.deleteAllTasks()
        }
    }

}