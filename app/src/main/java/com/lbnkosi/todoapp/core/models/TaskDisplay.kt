package com.lbnkosi.todoapp.core.models

data class TaskDisplay(
    var title: String = "",
    var taskStatusBg: Int? = null,
    var taskDateUpdated: String = ""
)