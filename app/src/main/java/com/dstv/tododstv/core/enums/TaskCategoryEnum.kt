package com.dstv.tododstv.core.enums

import com.dstv.tododstv.R

enum class TaskCategoryEnum(val defaultName: String = "", val colour: Int, val id: Int = 0) {
    CATEGORY_ONE("Personal", R.color.material_orange_primary_700, 1),
    CATEGORY_TWO("Business", R.color.material_pink_primary_700, 2),
    CATEGORY_THREE("Work", R.color.material_purple_primary_700, 3),
    CATEGORY_FOUR("School", R.color.material_indigo_primary_700, 4),
    CATEGORY_FIVE("Other", R.color.material_teal_primary_700, 5),
}