package com.lbnkosi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lbnkosi.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "TASK_DB"
    }

    abstract fun taskDao(): TaskDao
}