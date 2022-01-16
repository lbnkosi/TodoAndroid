package com.lbnkosi.todoapp.core.models

data class CategoryDisplay(
    var taskCount: String = "",
    var category: String = "",
    var complete: Int = 0,
    var count: Int = 0,
    var completeText: String = ""
)