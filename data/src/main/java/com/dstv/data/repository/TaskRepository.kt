package com.dstv.data.repository

import com.dstv.data.mappers.map
import com.dstv.data.source.TaskDataSource
import com.dstv.domain.model.Task
import com.dstv.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dataSource: TaskDataSource) : ITaskRepository {

    override suspend fun getTasks() = dataSource.getTasks().map {
        it.map()
    }

    override suspend fun createTask(task: Task) = dataSource.createTask(task).map {
        it.map()
    }

    override suspend fun updateTask(task: Task) = dataSource.updateTask(task).map {
        it.map()
    }

    override suspend fun deleteAllTasks() = dataSource.deleteAllTasks().map {
        it.map()
    }

    override suspend fun deleteTask(task: Task) = dataSource.deleteTask(task).map {
        it.map()
    }

    override suspend fun deleteTasks(taskList: List<Task>) = dataSource.deleteTasks(taskList).map {
        it.map()
    }

    override suspend fun searchTasks(keywords: String) = dataSource.searchTask(keywords).map {
        it.map()
    }
}