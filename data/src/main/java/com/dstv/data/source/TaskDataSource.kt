package com.dstv.data.source

import com.dstv.data.db.TaskDao
import com.dstv.data.mappers.TaskMapper
import com.dstv.data.model.entity.TaskEntity
import com.dstv.domain.model.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskDataSource @Inject constructor(
    private val taskDao: TaskDao
) {

    /**
     * Get one task from the db
     */
    suspend fun getTask(): Flow<TaskEntity> {
        return flow { emit(TaskEntity()) }
    }

    /**
     * Get all tasks from the db
     */
    suspend fun getTasks(): Flow<ArrayList<TaskEntity>> {
        val tasks = taskDao.getTasks() as ArrayList<TaskEntity>
        return flow { emit(tasks) }
    }

    /**
     * Create and store task to the db
     */
    suspend fun createTask(task: Task): Flow<ArrayList<TaskEntity>> {
        taskDao.createTask(TaskMapper.toEntity(task))
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    /**
     * Get task from db and update it
     */
    suspend fun updateTask(task: Task): Flow<ArrayList<TaskEntity>> {
        taskDao.updateTask(TaskMapper.toEntity(task))
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    /**
     * Delete a task with a matching id
     */
    suspend fun deleteTask(task: Task): Flow<ArrayList<TaskEntity>> {
        taskDao.deleteTask(task.id)
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    /**
     * Delete the selected tasks
     */
    suspend fun deleteTasks(taskList: List<Task>): Flow<ArrayList<TaskEntity>> {
        taskList.forEach { taskDao.deleteTask(it.id) }
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    /**
     * Delete all the tasks in the db
     */
    suspend fun deleteAllTasks(): Flow<ArrayList<TaskEntity>> {
        taskDao.deleteAllTasks()
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    suspend fun searchTask(keywords: String): Flow<ArrayList<TaskEntity>> {
        if (keywords.isEmpty()) {
            return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
        } else {
            val tasks = taskDao.getTasks() as ArrayList<TaskEntity>
            val searchFilter: ArrayList<TaskEntity> = arrayListOf()
            tasks.forEach {
                if (it.title.contains(keywords, ignoreCase = true)) searchFilter.add(it)
            }
            return flow { emit(searchFilter) }
        }
    }
}