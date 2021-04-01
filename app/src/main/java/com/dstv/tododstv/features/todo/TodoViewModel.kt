package com.dstv.tododstv.features.todo

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstv.domain.usecase.TaskUseCase
import com.dstv.tododstv.core.enums.TaskCategoryEnum
import com.dstv.tododstv.core.mappers.presenter.TaskMapper
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.core.util.CategoryCount
import com.dstv.tododstv.features.common.SearchRequest
import com.dstv.tododstv.features.common.TaskRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCase: TaskUseCase
) : ViewModel() {

    var isMultiSelectEnabled: Boolean = false

    var multiSelectTaskList: ArrayList<Task> = arrayListOf()

    var category: Int = 1

    var task: Task = Task()

    var isEdit: Boolean = false

    var isComplete: Boolean = false

    var taskRequest: TaskRequest = TaskRequest()

    var searchRequest: SearchRequest = SearchRequest()

    val success: LiveData<Boolean> get() = _success

    val taskList: LiveData<ArrayList<Task>> get() = _taskList

    val checkedCategory: LiveData<Int> get() = _checkedCategory

    val categoryList: LiveData<List<Category>> get() = _categoryList

    val taskDeleteList: LiveData<ArrayList<Task>> get() = _taskDeleteList

    val selectAll: LiveData<Boolean> get() = _selectAll

    private var _checkedCategory: MutableLiveData<Int> = MutableLiveData(1)

    private var _selectAll: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _success: MutableLiveData<Boolean> = MutableLiveData()

    private var _taskList: MutableLiveData<ArrayList<Task>> = MutableLiveData()

    private var _categoryList: MutableLiveData<List<Category>> = MutableLiveData()

    private var _taskDeleteList: MutableLiveData<ArrayList<Task>> = MutableLiveData(arrayListOf())

    fun setCategory(taskCat: TaskCategoryEnum) {
        category = taskCat.id
        _checkedCategory.value = taskCat.id
    }

    fun setIsComplete(aIsComplete: Boolean) {
        isComplete = aIsComplete
    }

    fun setIsEdit(aEdit: Boolean) {
        isEdit = aEdit
    }

    fun setATask(aTask: Task?) {
        if (aTask != null) {
            task = aTask
            configureTask(false)
        } else {
            configureTask(true)
        }
    }

    fun setMultiSelect(isEnabled: Boolean) {
        isMultiSelectEnabled = isEnabled
    }

    fun getMultiSelectIsEnabled(): Boolean {
        return isMultiSelectEnabled
    }

    fun setMultiSelectTaskList(task: Task) {
        if (!multiSelectTaskList.contains(task))
        multiSelectTaskList.add(task)
        _taskDeleteList.value = multiSelectTaskList
    }

    fun removeFromSelectedList(task: Task) {
        multiSelectTaskList.remove(task)
        _taskDeleteList.value = multiSelectTaskList
        if (multiSelectTaskList.isEmpty()) isMultiSelectEnabled = false
    }

    fun getSelectedTaskList(): ArrayList<Task> {
        return multiSelectTaskList
    }

    fun setSelectAll() {
        _selectAll.value = selectAll.value == false
    }

    private fun configureTask(isNull: Boolean) {
        isComplete = if (isNull) false else task.isComplete
        taskRequest.title = if (isNull) "" else task.title
        taskRequest.note = if (isNull) "" else task.note
        category = if (isNull) 1 else task.category
        if (!isNull) _checkedCategory.value = task.category else _checkedCategory.value = 1
    }

    private fun setTaskList(list: ArrayList<Task>) {
        _taskList.value = list
        _categoryList.value = CategoryCount.get(list)
        _success.value = false
    }

    private fun createTask() {
        viewModelScope.launch {
            useCase.createTask(TaskMapper.toDomain(task)).collect {
                _success.value = true
                setTaskList(TaskMapper.toPresenter(it))
            }
        }
    }

    private fun updateTask() {
        viewModelScope.launch {
            useCase.updateTask(TaskMapper.toDomain(task)).collect {
                _success.value = true
                setTaskList(TaskMapper.toPresenter(it))
            }
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            useCase.deleteTask(TaskMapper.toDomain(task)).collect {
                _success.value = true
                setTaskList(TaskMapper.toPresenter(it))
            }
        }
    }

    fun deleteSelectedTasks() {
        viewModelScope.launch {
            useCase.deleteTasks(TaskMapper.toDomain(multiSelectTaskList)).collect {
                _success.value = true
                setTaskList(TaskMapper.toPresenter(it))
            }
            _selectAll.value = selectAll.value == false
        }
    }

    private fun observeSearchKeyword() {
        searchRequest.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                searchTasks()
            }
        })
    }

    fun searchTasks() {
        viewModelScope.launch {
            useCase.searchTasks(searchRequest.keyword).collect {
                _success.value = true
                setTaskList(TaskMapper.toPresenter(it))
            }
        }
    }

    private fun getTasks() {
        viewModelScope.launch {
            useCase.getTasks().collect {
                setTaskList(TaskMapper.toPresenter(it))
            }
        }
    }

    fun createTaskModel() {
        if (taskRequest.isRequestValid()) {
            val currentTime = Calendar.getInstance().time.toString()
            if (!isEdit) {
                task = Task(id = 0, title = taskRequest.title, note = taskRequest.note, isComplete = isComplete, category = category, dateCreated = currentTime, dateUpdated = currentTime, subTaskList = arrayListOf())
                createTask()
            } else {
                task = Task(id = task.id, title = taskRequest.title, note = taskRequest.note, isComplete = isComplete, category = category, dateUpdated = currentTime, dateCreated = task.dateCreated, subTaskList = arrayListOf())
                updateTask()
            }
        }
    }

    init {
        getTasks()
        observeSearchKeyword()
    }

}