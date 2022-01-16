package com.lbnkosi.todoapp.core.enums

import com.lbnkosi.todoapp.R

enum class TaskCategoryEnum(val defaultName: String = "", val colour: Int, val id: Int = 0) {
    CATEGORY_1("Personal", R.color.material_orange_primary_700, 1),
    CATEGORY_2("Business", R.color.material_pink_primary_700, 2),
    CATEGORY_3("Work", R.color.material_purple_primary_700, 3),
    CATEGORY_4("School", R.color.material_indigo_primary_700, 4),
    CATEGORY_5("Other", R.color.material_teal_primary_700, 5),
}