package com.dstv.data.mappers

import com.dstv.data.model.entity.TaskEntity
import com.dstv.domain.model.task.Task

object TaskMapper {

    fun toDomainResource(resource: TaskEntity): Task {
        return Task(
            resource.id,
            resource.title,
            resource.note,
            resource.complete,
            resource.category,
            resource.dateCreated,
            resource.dateUpdated,
            //resource.subTaskList
        )
    }

    fun toDomainListResource(resource: ArrayList<TaskEntity>): ArrayList<Task> {
        val newList: ArrayList<Task> = arrayListOf()
        //Check if success
        resource.forEach {
            newList.add(
                Task(
                    it.id,
                    it.title,
                    it.note,
                    it.complete,
                    it.category,
                    it.dateCreated,
                    it.dateUpdated,
                    //it.subTaskList
                )
            )
        }
        return newList
    }

    fun toEntity(task: Task): TaskEntity {
        return TaskEntity(
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

    fun toEntityList(): ArrayList<TaskEntity> {
        return arrayListOf()
    }

}