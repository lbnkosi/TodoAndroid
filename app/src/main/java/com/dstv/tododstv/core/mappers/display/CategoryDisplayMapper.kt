package com.dstv.tododstv.core.mappers.display

import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.CategoryDisplay
import java8.util.function.Function

class CategoryDisplayMapper {

    fun toCategoryDisplay(category: Category): CategoryDisplay = categoryDisplayMapper.apply(category)

    private val categoryDisplayMapper = Function<Category, CategoryDisplay> {
        CategoryDisplay().apply {
            category = it.title
            taskCount = "${it.count} Tasks"
            complete = if (it.count == 0) 0 else getPercentage(it.complete, it.count)
            completeText = "${complete}%"
        }
    }

    private fun getPercentage(complete: Int, count: Int): Int {
        return (complete.toDouble() / count.toDouble() * 100).toInt()
    }
}