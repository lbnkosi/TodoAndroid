package com.dstv.domain.repository

import com.dstv.domain.model.resource.Resource
import com.dstv.domain.model.task.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {

    //Getting
    suspend fun getTask(): Flow<Task>

    suspend fun getTasks(): Flow<ArrayList<Task>>

    //Adding
    suspend fun createTask(task: Task): Flow<ArrayList<Task>>

    //Updating
    suspend fun updateTask(task: Task): Flow<ArrayList<Task>>

    //Deleting
    suspend fun deleteAllTasks()

    suspend fun deleteTask(task: Task): Flow<ArrayList<Task>>

    suspend fun deleteTasks(taskList: List<Task>): Flow<ArrayList<Task>>

    //Search
    suspend fun searchTasks(keywords: String): Flow<ArrayList<Task>>
}