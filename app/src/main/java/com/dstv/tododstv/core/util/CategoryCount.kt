package com.dstv.tododstv.core.util

import com.dstv.tododstv.core.enums.TaskCategoryEnum.*
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task

object CategoryCount {

    private var one: Int = 0
    private var two: Int = 0
    private var three: Int = 0
    private var four: Int = 0
    private var five: Int = 0

    private var catOneComplete: Int = 0
    private var catTwoComplete: Int = 0
    private var catThreeComplete: Int = 0
    private var catFourComplete: Int = 0
    private var catFiveComplete: Int = 0

    fun get(taskList: ArrayList<Task>): List<Category> {
        resetCounts()
        val categoryList: ArrayList<Category> = arrayListOf()
        taskList.forEach {
            when (it.category) {
                1 -> categoryOne(it)
                2 -> categoryTwo(it)
                3 -> categoryThree(it)
                4 -> categoryFour(it)
                5 -> categoryFive(it)
            }
        }
        categoryList.add(Category(title = CATEGORY_ONE.defaultName, count = one, complete = catOneComplete))
        categoryList.add(Category(title = CATEGORY_TWO.defaultName, count = two, complete = catTwoComplete))
        categoryList.add(Category(title = CATEGORY_THREE.defaultName, count = three, complete = catThreeComplete))
        categoryList.add(Category(title = CATEGORY_FOUR.defaultName, count = four, complete = catFourComplete))
        categoryList.add(Category(title = CATEGORY_FIVE.defaultName, count = five, complete = catFiveComplete))
        return categoryList.sortedWith(compareBy { it.count }).reversed()
    }

    private fun resetCounts(){
        one = 0
        two = 0
        three = 0
        four = 0
        five = 0

        catOneComplete = 0
        catTwoComplete = 0
        catThreeComplete = 0
        catFourComplete = 0
        catFiveComplete = 0
    }

    private fun categoryOne(task: Task) {
        if (task.isComplete) catOneComplete++
        one++
    }

    private fun categoryTwo(task: Task) {
        if (task.isComplete) catTwoComplete++
        two++
    }

    private fun categoryThree(task: Task) {
        if (task.isComplete) catThreeComplete++
        three++
    }

    private fun categoryFour(task: Task) {
        if (task.isComplete) catFourComplete++
        four++
    }

    private fun categoryFive(task: Task) {
        if (task.isComplete) catFiveComplete++
        five++
    }
}