package com.lbnkosi.todoapp.features.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbnkosi.domain.usecase.TaskUseCase
import com.lbnkosi.todoapp.core.enums.TaskCategoryEnum
import com.lbnkosi.todoapp.core.extensions.getDate
import com.lbnkosi.todoapp.core.mappers.presenter.TaskMapper.map
import com.lbnkosi.todoapp.core.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageTaskViewModel @Inject constructor(private val useCase: TaskUseCase) : ViewModel() {

    var category: Int = 1

    var task: Task = Task()

    var isEdit: Boolean = false

    var isComplete: Boolean = false

    val success: LiveData<Boolean> get() = _success

    val checkedCategory: LiveData<Int> get() = _checkedCategory

    var taskBaseObservable: TaskBaseObservable = TaskBaseObservable()

    private var _success: MutableLiveData<Boolean> = MutableLiveData()

    private var _checkedCategory: MutableLiveData<Int> = MutableLiveData(1)

    fun setIsComplete(aIsComplete: Boolean) {
        isComplete = aIsComplete
    }

    fun setIsEdit(aEdit: Boolean) {
        isEdit = aEdit
    }

    fun setATask(aTask: Task?) {
        if (aTask != null) {
            task = aTask
            false.configureTask()
        } else {
            true.configureTask()
        }
    }

    fun setCategory(taskCat: TaskCategoryEnum) {
        category = taskCat.id
        _checkedCategory.value = taskCat.id
    }

    private fun Boolean.configureTask() {
        isComplete = if (this) false else task.isComplete
        taskBaseObservable.title = if (this) "" else task.title
        taskBaseObservable.note = if (this) "" else task.note
        category = if (this) 1 else task.category
        if (!this) _checkedCategory.value = task.category else _checkedCategory.value = 1
    }

    fun createTaskModel() {
        if (taskBaseObservable.isRequestValid()) {
            val currentTime = getDate()
            task = Task(if (isEdit) task.id else 0, taskBaseObservable.title, taskBaseObservable.note, isComplete, category, currentTime, if (isEdit) task.dateCreated else currentTime)
            if (isEdit) updateTask() else createTask()
        }
    }

    private fun onSuccess() {
        _success.value = true
        _success.value = false
    }

    private fun createTask() {
        viewModelScope.launch {
            useCase.createTask(task.map()).collect { onSuccess() }
        }
    }

    private fun updateTask() {
        viewModelScope.launch {
            useCase.updateTask(task.map()).collect { onSuccess() }
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            useCase.deleteTask(task.map()).collect { onSuccess() }
        }
    }

}