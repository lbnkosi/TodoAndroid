package com.dstv.tododstv.features.todo

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dstv.domain.usecase.TaskUseCase
import com.dstv.tododstv.core.enums.TaskSortEnum
import com.dstv.tododstv.core.mappers.presenter.TaskMapper.map
import com.dstv.tododstv.core.mappers.presenter.TaskMapper.toDomain
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.core.util.CategoryCount.get
import com.dstv.tododstv.core.util.TaskSort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val useCase: TaskUseCase) : ViewModel() {

    var multiSelectEnabled: Boolean = false

    var toDeleteList: ArrayList<Task> = arrayListOf()

    val selectAll: LiveData<Boolean> get() = _selectAll

    val taskList: LiveData<ArrayList<Task>> get() = _taskList

    val categoryList: LiveData<List<Category>> get() = _categoryList

    val taskDeleteList: LiveData<ArrayList<Task>> get() = _taskDeleteList

    var searchBaseObservable: SearchBaseObservable = SearchBaseObservable()

    private var _taskList: MutableLiveData<ArrayList<Task>> = MutableLiveData()

    private var _categoryList: MutableLiveData<List<Category>> = MutableLiveData()

    private var _selectAll: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _taskDeleteList: MutableLiveData<ArrayList<Task>> = MutableLiveData(arrayListOf())

    fun addRemoveFromDeleteList(isSelected: Boolean, task: Task) {
        if (isSelected) addToDeleteList(task) else removeFromDeleteList(task)
    }

    private fun addToDeleteList(task: Task) {
        if (!toDeleteList.contains(task)) toDeleteList.add(task)
        _taskDeleteList.value = toDeleteList
    }

    private fun removeFromDeleteList(task: Task) {
        toDeleteList.remove(task)
        _taskDeleteList.value = toDeleteList
        if (toDeleteList.isEmpty()) multiSelectEnabled = false
    }

    fun setSelectAll() {
        _selectAll.value = selectAll.value == false
    }

    fun sortTask(type: TaskSortEnum) {
        TaskSort.sortTasks(type, _taskList.value!!).updateObserver()
    }

    private fun ArrayList<Task>.updateObserver() {
        _taskList.value = this
        _categoryList.value = get()
    }

    fun getTasks() {
        viewModelScope.launch {
            useCase.getTasks().collect {
                it.map().updateObserver()
            }
        }
    }

    fun deleteSelectedTasks() {
        viewModelScope.launch {
            useCase.deleteTasks(toDeleteList.toDomain()).collect {
                it.map().updateObserver()
            }
            _selectAll.value = false
        }
    }

    fun searchTasks() {
        viewModelScope.launch {
            useCase.searchTasks(searchBaseObservable.keyword).collect {
                it.map().updateObserver()
            }
        }
    }

    private fun observeSearchKeyword() {
        searchBaseObservable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                searchTasks()
            }
        })
    }

    init {
        getTasks()
        observeSearchKeyword()
    }
}