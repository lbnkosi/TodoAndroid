package com.lbnkosi.todoapp.core.util

import com.lbnkosi.todoapp.R

object TaskStatus {

    fun get(isComplete: Boolean, categoryId: Int): Int {
        if (isComplete) return R.drawable.task_completed
        return when (categoryId) {
            1 -> R.drawable.category_one_rounded_bg
            2 -> R.drawable.category_two_rounded_bg
            3 -> R.drawable.category_three_rounded_bg
            4 -> R.drawable.category_four_rounded_bg
            5 -> R.drawable.category_five_rounded_bg
            6 -> R.drawable.category_six_rounded_bg
            else -> R.drawable.category_one_rounded_bg
        }
    }

}