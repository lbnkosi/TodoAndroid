package com.dstv.tododstv.core.mappers.presenter

import com.dstv.tododstv.core.models.Task

object TaskMapper {
    fun Task.map(): com.dstv.domain.model.Task {
        return com.dstv.domain.model.Task(id, title, note, isComplete, category, dateCreated, dateUpdated)
    }

    fun com.dstv.domain.model.Task.map(): Task {
        return Task(id, title, note, isComplete, category, dateCreated, dateUpdated)
    }

    fun ArrayList<com.dstv.domain.model.Task>.map(): ArrayList<Task> {
        return map { item -> item.map() } as ArrayList<Task>
    }

    fun ArrayList<Task>.toDomain(): ArrayList<com.dstv.domain.model.Task> {
        return map { item -> item.map() } as ArrayList<com.dstv.domain.model.Task>
    }
}