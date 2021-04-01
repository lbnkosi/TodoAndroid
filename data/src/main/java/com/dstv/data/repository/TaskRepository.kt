package com.dstv.data.repository

import com.dstv.data.mappers.TaskMapper
import com.dstv.data.source.TaskDataSource
import com.dstv.domain.model.task.Task
import com.dstv.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dataSource: TaskDataSource
) : ITaskRepository {
    override suspend fun getTask(): Flow<Task> {
        return dataSource.getTask().map {
            TaskMapper.toDomainResource(it)
        }
    }

    override suspend fun getTasks(): Flow<ArrayList<Task>> {
        return dataSource.getTasks().map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun createTask(task: Task): Flow<ArrayList<Task>> {
        return dataSource.createTask(task).map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun updateTask(task: Task): Flow<ArrayList<Task>> {
        return dataSource.updateTask(task).map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun deleteAllTasks(): Flow<ArrayList<Task>> {
        return dataSource.deleteAllTasks().map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun deleteTask(task: Task): Flow<ArrayList<Task>> {
        return dataSource.deleteTask(task).map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun deleteTasks(taskList: List<Task>): Flow<ArrayList<Task>> {
        return dataSource.deleteTasks(taskList).map {
            TaskMapper.toDomainListResource(it)
        }
    }

    override suspend fun searchTasks(keywords: String): Flow<ArrayList<Task>> {
        return dataSource.searchTask(keywords).map {
            TaskMapper.toDomainListResource(it)
        }
    }
}