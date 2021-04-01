package com.dstv.domain.model.task

data class Task(
    var id: Int = 0,
    var title: String = "",
    var note: String = "",
    var isComplete: Boolean = false,
    var category: Int = 0,
    var dateCreated: String = "",
    var dateUpdated: String = "",
)
