package com.lbnkosi.todoapp.features.common

interface TaskCallback {
    fun onComplete(refresh : Boolean = true)
}