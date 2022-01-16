package com.lbnkosi.todoapp.core.enums

import android.content.Context
import androidx.core.content.ContextCompat
import com.lbnkosi.todoapp.R

enum class MultiSelectEnum(val tag: String = "", val background: Int = R.drawable.blue_button_10dp) {
    SELECTED("selected", R.drawable.red_button_10dp),
    NOT_SELECTED("not_selected", R.drawable.blue_button_10dp);

    companion object {
        fun Context.selected() = ContextCompat.getDrawable(this, SELECTED.background)!!
        fun Context.notSelected() = ContextCompat.getDrawable(this, NOT_SELECTED.background)!!
    }

}