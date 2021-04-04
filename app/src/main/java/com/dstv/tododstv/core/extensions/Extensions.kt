package com.dstv.tododstv.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dstv.tododstv.core.enums.MultiSelectEnum
import com.dstv.tododstv.core.enums.MultiSelectEnum.Companion.notSelected
import com.dstv.tododstv.core.enums.MultiSelectEnum.Companion.selected
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import com.dstv.tododstv.features.common.BaseActivity
import com.dstv.tododstv.features.common.BaseFragment
import com.dstv.tododstv.features.common.TaskCallback
import com.dstv.tododstv.features.task.ManageTaskFragment
import com.dstv.tododstv.features.todo.TodoViewModel
import dagger.hilt.android.internal.managers.FragmentComponentManager
import java.text.DateFormat
import java.util.*

fun getDate(): String {
    val date = Date()
    return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date)
}

fun ArrayList<Category>.sortedCategory(): List<Category> {
    return this.sortedWith(compareBy { it.count }).reversed()
}

fun Boolean.setStatus(): MultiSelectEnum {
    return (if (this) MultiSelectEnum.SELECTED else MultiSelectEnum.NOT_SELECTED)
}

fun Boolean.setBackground(context: Context): Drawable {
   return (if (this) context.selected() else context.notSelected())
}

fun MultiSelectEnum.getStatus(): MultiSelectEnum {
    return if (this == MultiSelectEnum.SELECTED) MultiSelectEnum.NOT_SELECTED else MultiSelectEnum.SELECTED
}

fun getViewModel(context: Context): ViewModel {
    return ViewModelProvider((FragmentComponentManager.findActivity(context) as FragmentActivity)).get(TodoViewModel::class.java)
}

fun getActivity(context: Context): FragmentActivity {
    return (FragmentComponentManager.findActivity(context) as FragmentActivity)
}

fun getManageFragment(task: Task, callback: TaskCallback): ManageTaskFragment {
    return ManageTaskFragment.newInstance(true, task, callback)
}

fun MultiSelectEnum.isSelected(): Boolean {
    return this == MultiSelectEnum.SELECTED
}