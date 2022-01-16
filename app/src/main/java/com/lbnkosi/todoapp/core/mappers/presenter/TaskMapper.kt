package com.lbnkosi.todoapp.core.mappers.presenter

import com.lbnkosi.todoapp.core.models.Task

object TaskMapper {
    fun Task.map(): com.lbnkosi.domain.model.Task {
        return com.lbnkosi.domain.model.Task(id, title, note, isComplete, category, dateCreated, dateUpdated)
    }

    fun com.lbnkosi.domain.model.Task.map(): Task {
        return Task(id, title, note, isComplete, category, dateCreated, dateUpdated)
    }

    fun ArrayList<com.lbnkosi.domain.model.Task>.map(): ArrayList<Task> {
        return map { item -> item.map() } as ArrayList<Task>
    }

    fun ArrayList<Task>.toDomain(): ArrayList<com.lbnkosi.domain.model.Task> {
        return map { item -> item.map() } as ArrayList<com.lbnkosi.domain.model.Task>
    }
}