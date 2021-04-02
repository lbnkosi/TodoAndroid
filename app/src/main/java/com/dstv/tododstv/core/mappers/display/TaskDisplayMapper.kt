package com.dstv.tododstv.core.mappers.display

import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.core.models.TaskDisplay
import com.dstv.tododstv.core.util.TaskStatus
import java8.util.function.Function

class TaskDisplayMapper {

    fun toTaskDisplay(task: Task): TaskDisplay = taskDisplayMapper.apply(task)

    private val taskDisplayMapper = Function<Task, TaskDisplay> {
        TaskDisplay().apply {
            title = it.title
            taskStatusBg = TaskStatus.get(it.isComplete, it.category)
            taskDateUpdated = it.dateUpdated
        }
    }

}