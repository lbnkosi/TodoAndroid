package com.dstv.tododstv.core.databinding

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dstv.tododstv.core.adapters.CategoryAdapter
import com.dstv.tododstv.core.adapters.TaskAdapter
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.models.Task
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
    @BindingAdapter("textChangedListener")
    fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
        editText.addTextChangedListener(textWatcher)
    }
}