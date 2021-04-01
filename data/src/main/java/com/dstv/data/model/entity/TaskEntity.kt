package com.dstv.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.dstv.domain.model.task.SubTask

@Entity(tableName = "Tasks")
class TaskEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "note")
    var note: String = "",

    @ColumnInfo(name = "complete")
    var complete: Boolean = false,

    @ColumnInfo(name = "color")
    var category: Int = 0,

    @ColumnInfo(name = "dateCreated")
    var dateCreated: String = "",

    @ColumnInfo(name = "dateUpdated")
    var dateUpdated: String = "",

    @Ignore
    var subTaskList: ArrayList<SubTask> = arrayListOf()
)
