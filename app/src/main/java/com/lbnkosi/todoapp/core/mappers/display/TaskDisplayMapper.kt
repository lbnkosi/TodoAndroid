package com.lbnkosi.todoapp.core.mappers.display

import com.lbnkosi.todoapp.core.models.Task
import com.lbnkosi.todoapp.core.models.TaskDisplay
import com.lbnkosi.todoapp.core.util.TaskStatus
import java8.util.function.Function

class TaskDisplayMapper {

    fun toTaskDisplay(task: Task): TaskDisplay = taskDisplayMapper.apply(task)

    private val taskDisplayMapper = Function<Task, TaskDisplay> {
        TaskDisplay().apply {
            taskStatusBg = TaskStatus.get(it.isComplete, it.category)
            taskDateUpdated = it.dateUpdated
            title = it.title
        }
    }

}