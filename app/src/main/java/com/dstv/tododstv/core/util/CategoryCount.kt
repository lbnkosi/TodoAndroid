package com.dstv.tododstv.core.util

import com.dstv.tododstv.core.enums.TaskCategoryEnum.*
import com.dstv.tododstv.core.extensions.sortedCategory
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task

object CategoryCount {

    private var category1TotalCount = 0

    private var category2TotalCount = 0

    private var category3TotalCount = 0

    private var category4TotalCount = 0

    private var category5TotalCount = 0

    private var category1CompletedCount = 0

    private var category2CompletedCount = 0

    private var category3CompletedCount = 0

    private var category4CompletedCount = 0

    private var category6CompletedCount = 0

    private var categoryCountList: ArrayList<Category> = arrayListOf()

    fun ArrayList<Task>.get(): List<Category> {
        resetCounts()
        this.count()
        addCategoriesToList()
        return categoryCountList.sortedCategory()
    }

    private fun resetCounts() {
        resetTotalCount()
        resetCompletedCount()
        categoryCountList = arrayListOf()
    }

    private fun ArrayList<Task>.count() {
        forEach {
            when (it.category) {
                CATEGORY_1.id -> it.category1()
                CATEGORY_2.id -> it.category2()
                CATEGORY_3.id -> it.category3()
                CATEGORY_4.id -> it.category4()
                CATEGORY_5.id -> it.category5()
            }
        }
    }

    private fun addCategoriesToList() {
        categoryCountList.run {
            add(Category(CATEGORY_1.defaultName, category1TotalCount, category1CompletedCount))
            add(Category(CATEGORY_2.defaultName, category2TotalCount, category2CompletedCount))
            add(Category(CATEGORY_3.defaultName, category3TotalCount, category3CompletedCount))
            add(Category(CATEGORY_4.defaultName, category4TotalCount, category4CompletedCount))
            add(Category(CATEGORY_5.defaultName, category5TotalCount, category6CompletedCount))
        }
    }

    private fun resetTotalCount() {
        category1TotalCount = 0
        category2TotalCount = 0
        category3TotalCount = 0
        category4TotalCount = 0
        category5TotalCount = 0
    }

    private fun resetCompletedCount() {
        category1CompletedCount = 0
        category2CompletedCount = 0
        category3CompletedCount = 0
        category4CompletedCount = 0
        category6CompletedCount = 0
    }

    private fun Task.category1() {
        if (isComplete) category1CompletedCount++
        category1TotalCount++
    }

    private fun Task.category2() {
        if (isComplete) category2CompletedCount++
        category2TotalCount++
    }

    private fun Task.category3() {
        if (isComplete) category3CompletedCount++
        category3TotalCount++
    }

    private fun Task.category4() {
        if (isComplete) category4CompletedCount++
        category4TotalCount++
    }

    private fun Task.category5() {
        if (isComplete) category6CompletedCount++
        category5TotalCount++
    }
}