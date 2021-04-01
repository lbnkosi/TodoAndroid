package com.dstv.data.db

import androidx.room.*
import com.dstv.data.model.entity.TaskEntity

@Dao
interface TaskDao {

    //Get Task(s)
    @Query("SELECT * FROM Tasks")
    suspend fun getTasks(): List<TaskEntity>

    @Query("SELECT * FROM Tasks WHERE id=:id")
    suspend fun getTask(id: Int): TaskEntity

    //Create Tasks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(taskEntity: TaskEntity)

    //Update Task
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskEntity: TaskEntity)

    //Delete Task(s)
    @Query("DELETE FROM Tasks")
    suspend fun deleteAllTasks()

    @Query("DELETE FROM Tasks WHERE id=:id")
    suspend fun deleteTask(id: Int)

    //Get Available Task Count
    @Query("SELECT COUNT(*) FROM Tasks")
    suspend fun tasksAvailable(): Int
}