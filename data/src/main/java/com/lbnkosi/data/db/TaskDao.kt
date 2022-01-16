package com.lbnkosi.data.db

import androidx.room.*
import com.lbnkosi.data.entity.TaskEntity

@Dao
interface TaskDao {
    //Get tasks
    @Query("SELECT * FROM Tasks")
    suspend fun getTasks(): List<TaskEntity>

    //Get task
    @Query("SELECT * FROM Tasks WHERE id=:id")
    suspend fun getTask(id: Int): TaskEntity

    //Create task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(taskEntity: TaskEntity)

    //Update task
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskEntity: TaskEntity)

    //Delete all tasks
    @Query("DELETE FROM Tasks")
    suspend fun deleteAllTasks()

    //Delete task
    @Query("DELETE FROM Tasks WHERE id=:id")
    suspend fun deleteTask(id: Int)
}