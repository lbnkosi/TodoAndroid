package com.lbnkosi.domain.usecase

import com.lbnkosi.domain.model.Task
import com.lbnkosi.domain.repository.ITaskRepository
import javax.inject.Inject

class TaskUseCase @Inject constructor(private val repository: ITaskRepository) {
    suspend fun getTasks() = repository.getTasks()

    suspend fun createTask(task: Task) = repository.createTask(task)

    suspend fun updateTask(task: Task) = repository.updateTask(task)

    suspend fun deleteTask(task: Task) = repository.deleteTask(task)

    suspend fun deleteAllTasks() = repository.deleteAllTasks()

    suspend fun deleteTasks(taskList: List<Task>) = repository.deleteTasks(taskList)

    suspend fun searchTasks(keyword: String) = repository.searchTasks(keyword)
}