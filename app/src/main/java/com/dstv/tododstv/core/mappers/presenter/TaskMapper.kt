package com.dstv.tododstv.core.mappers.presenter

import com.dstv.tododstv.core.models.Task

object TaskMapper {

    fun toPresenter(task: com.dstv.domain.model.task.Task): Task {
        return Task(
            task.id,
            task.title,
            task.note,
            task.isComplete,
            task.category,
            task.dateCreated,
            task.dateUpdated,
            //task.subTaskList
        )
    }

    fun toPresenter(taskList: ArrayList<com.dstv.domain.model.task.Task>): ArrayList<Task> {
        val newList: ArrayList<Task> = arrayListOf()
        taskList.forEach {
            newList.add(
                Task(
                    it.id,
                    it.title,
                    it.note,
                    it.isComplete,
                    it.category,
                    it.dateCreated,
                    it.dateUpdated,
                    //it.subTaskList
                )
            )
        }
        return newList
    }

    fun toDomain(task: Task): com.dstv.domain.model.task.Task {
        return com.dstv.domain.model.task.Task(
            task.id,
            task.title,
            task.note,
            task.isComplete,
            task.category,
            task.dateCreated,
            task.dateUpdated,
            //task.subTaskList
        )
    }

    fun toDomain(presenterList: ArrayList<Task>): ArrayList<com.dstv.domain.model.task.Task> {
        val domainList: ArrayList<com.dstv.domain.model.task.Task> = arrayListOf()
        presenterList.forEach {
            domainList.add(
                com.dstv.domain.model.task.Task(
                    it.id,
                    it.title,
                    it.note,
                    it.isComplete,
                    it.category,
                    it.dateCreated,
                    it.dateUpdated,
                )
            )
        }
        return domainList
    }
}