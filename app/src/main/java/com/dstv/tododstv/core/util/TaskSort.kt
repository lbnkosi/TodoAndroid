package com.dstv.tododstv.core.util

import com.dstv.tododstv.core.enums.TaskSortEnum
import com.dstv.tododstv.core.enums.TaskSortEnum.*
import com.dstv.tododstv.core.models.Task

object TaskSort {

    fun sortTask(type: TaskSortEnum, taskList: ArrayList<Task>): ArrayList<Task> {
        if (taskList.isEmpty()) return arrayListOf()
        return when (type) {
            CATEGORY -> sortByCategory(taskList)
            COMPLETED -> sortByCompleted(taskList)
            DATE_CREATED_ASCENDING -> sortByDateCreatedAsc(taskList)
            DATE_UPDATED_ASCENDING -> sortByDateUpdatedAsc(taskList)
        }
    }

    private fun sortByCategory(taskList: ArrayList<Task>): ArrayList<Task> {
        return taskList.sortedWith(compareBy { it.category }).reversed() as ArrayList<Task>
    }

    private fun sortByCompleted(taskList: ArrayList<Task>): ArrayList<Task> {
        return taskList.sortedWith(compareBy { it.isComplete }).reversed() as ArrayList<Task>
    }

    private fun sortByDateCreatedAsc(taskList: ArrayList<Task>): ArrayList<Task> {
        return taskList.sortedWith(compareBy { it.dateCreated }).reversed() as ArrayList<Task>
    }

    private fun sortByDateUpdatedAsc(taskList: ArrayList<Task>): ArrayList<Task> {
        return taskList.sortedWith(compareBy { it.dateUpdated }).reversed() as ArrayList<Task>
    }

}