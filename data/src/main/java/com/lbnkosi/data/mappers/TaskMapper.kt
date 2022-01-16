package com.lbnkosi.data.mappers

import com.lbnkosi.data.entity.TaskEntity
import com.lbnkosi.domain.model.Task

fun TaskEntity.map(): Task {
    return Task(id, title, note, complete, category, dateCreated, dateUpdated)
}

fun ArrayList<TaskEntity>.map(): ArrayList<Task> {
    return map { item -> item.map() } as ArrayList<Task>
}

fun Task.map(): TaskEntity {
    return TaskEntity(id, title, note, isComplete, category, dateCreated, dateUpdated)
}
