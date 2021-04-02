package com.dstv.tododstv.core.databinding

import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dstv.tododstv.core.adapters.CategoryAdapter
import com.dstv.tododstv.core.adapters.TaskAdapter
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.internal.managers.FragmentComponentManager

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("setTaskBackground")
    fun setTaskBackground(view: View, drawable: Int) {
        view.background = ContextCompat.getDrawable(view.context, drawable)
    }

    @JvmStatic
    @BindingAdapter("bindTaskRecyclerView")
    fun bindTaskRecyclerView(recyclerView: RecyclerView, taskList: ArrayList<Task>?) {
        val fragmentManager = (FragmentComponentManager.findActivity(recyclerView.context) as FragmentActivity).supportFragmentManager
        recyclerView.adapter = TaskAdapter(fragmentManager)
        (recyclerView.adapter as TaskAdapter).replace(taskList)
    }

    @JvmStatic
    @BindingAdapter("bindCategoryRecyclerView")
    fun bindCategoryRecyclerView(recyclerView: RecyclerView, categoryList: List<Category>?) {
        recyclerView.adapter = CategoryAdapter()
        (recyclerView.adapter as CategoryAdapter).replace(categoryList)
    }

    @JvmStatic
    @BindingAdapter("bindTotalProgress")
    fun bindTotalProgress(linearProgressIndicator: LinearProgressIndicator, taskList: ArrayList<Task>?) {
        var totalTasks = 0
        var completedTasks = 0
        taskList?.forEach {
            totalTasks++
            if (it.isComplete) completedTasks++
        }
        linearProgressIndicator.setProgressCompat(((completedTasks.toDouble() / totalTasks.toDouble()) * 100).toInt(), true)
    }

    @JvmStatic
    @BindingAdapter("showHideGroup")
    fun showHideGroup(group: Group, showHide: Boolean) {
        group.isVisible = showHide
    }

}