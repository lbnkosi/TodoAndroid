package com.dstv.tododstv.features.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstv.domain.usecase.TaskUseCase
import com.dstv.tododstv.core.enums.TaskCategoryEnum
import com.dstv.tododstv.core.extensions.getDate
import com.dstv.tododstv.core.mappers.presenter.TaskMapper
import com.dstv.tododstv.core.mappers.presenter.TaskMapper.map
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.features.common.TaskRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val useCase: TaskUseCase
): ViewModel() {

    var category: Int = 1

    var task: Task = Task()

    var isEdit: Boolean = false

    var isComplete: Boolean = false

    var taskRequest: TaskRequest = TaskRequest()

    val success: LiveData<Boolean> get() = _success

    val checkedCategory: LiveData<Int> get() = _checkedCategory

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
        taskRequest.title = if (this) "" else task.title
        taskRequest.note = if (this) "" else task.note
        category = if (this) 1 else task.category
        if (!this) _checkedCategory.value = task.category else _checkedCategory.value = 1
    }

    fun createTaskModel() {
        if (taskRequest.isRequestValid()) {
            val currentTime = getDate()
            task = Task(if (isEdit) task.id else 0, taskRequest.title, taskRequest.note, isComplete, category, currentTime, if (isEdit) task.dateCreated else currentTime)
            if (isEdit) updateTask() else createTask()
        }
    }

    private fun onSuccess() {
        _success.value = true
        _success.value = false
    }

    //Store a task entity in the db
    private fun createTask() {
        viewModelScope.launch {
            useCase.createTask(TaskMapper.toDomain(task)).collect { onSuccess() }
        }
    }

    //Update a task entity in the db
    private fun updateTask() {
        viewModelScope.launch {
            useCase.updateTask(TaskMapper.toDomain(task)).collect { onSuccess() }
        }
    }

    //Delete a task entity from the db
    fun deleteTask() {
        viewModelScope.launch {
            useCase.deleteTask(TaskMapper.toDomain(task)).collect { onSuccess() }
        }
    }

}