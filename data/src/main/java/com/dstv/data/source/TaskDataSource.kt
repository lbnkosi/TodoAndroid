package com.dstv.data.source

import com.dstv.data.db.TaskDao
import com.dstv.data.entity.TaskEntity
import com.dstv.data.mappers.map
import com.dstv.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskDataSource @Inject constructor(private val taskDao: TaskDao) {

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
        taskDao.createTask(task.map())
        return flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
    }

    /**
     * Get task from db and update it
     */
    suspend fun updateTask(task: Task): Flow<ArrayList<TaskEntity>> {
        taskDao.updateTask(task.map())
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

    /**
     * Search for tasks containing a keyword in the db
     */
    suspend fun searchTask(keywords: String): Flow<ArrayList<TaskEntity>> {
        return if (keywords.isEmpty()) {
            flow { emit(taskDao.getTasks() as ArrayList<TaskEntity>) }
        } else {
            flow { emit(taskDao.getTasks().filter { it.title.contains(keywords, true) } as ArrayList<TaskEntity>) }
        }
    }
}