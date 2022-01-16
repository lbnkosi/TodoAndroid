package com.lbnkosi.domain.repository

import com.lbnkosi.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun getTasks(): Flow<ArrayList<Task>>

    suspend fun createTask(task: Task): Flow<ArrayList<Task>>

    suspend fun updateTask(task: Task): Flow<ArrayList<Task>>

    suspend fun deleteAllTasks(): Flow<ArrayList<Task>>

    suspend fun deleteTask(task: Task): Flow<ArrayList<Task>>

    suspend fun deleteTasks(taskList: List<Task>): Flow<ArrayList<Task>>

    suspend fun searchTasks(keywords: String): Flow<ArrayList<Task>>
}