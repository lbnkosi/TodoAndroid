package com.dstv.tododstv.core.mappers.display

import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.CategoryDisplay
import java8.util.function.Function
import kotlin.math.round

class CategoryDisplayMapper {

    fun toCategoryDisplay(category: Category): CategoryDisplay = categoryDisplayMapper.apply(category)

    private val categoryDisplayMapper = Function<Category, CategoryDisplay> {
        CategoryDisplay().apply {
            category = it.title
            taskCount = "${it.count} Tasks"
            complete = if (it.count == 0) 0 else ((it.complete.toDouble()/it.count.toDouble()) * 100).toInt()
            completeText = "${complete}%"
        }
    }

}