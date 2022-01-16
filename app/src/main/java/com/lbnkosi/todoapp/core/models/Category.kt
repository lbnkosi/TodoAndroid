package com.lbnkosi.todoapp.core.models

data class Category(
    var title: String = "",
    var count: Int = 0,
    var complete: Int = 0
)