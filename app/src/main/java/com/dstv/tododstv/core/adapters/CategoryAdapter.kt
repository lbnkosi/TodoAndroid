package com.dstv.tododstv.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dstv.tododstv.core.mappers.display.CategoryDisplayMapper
import com.dstv.tododstv.core.models.Category
import com.dstv.tododstv.core.util.DataBoundListAdapter
import com.dstv.tododstv.databinding.CategoryProgressRowBinding

class CategoryAdapter : DataBoundListAdapter<Category, CategoryProgressRowBinding>() {

    override fun createBinding(parent: ViewGroup?) = CategoryProgressRowBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

    override fun bind(binding: CategoryProgressRowBinding, item: Category) {
        val display = CategoryDisplayMapper().toCategoryDisplay(item)
        binding.categoryDisplay = display
        binding.progress.setProgressCompat(display.complete, true)
    }

    override fun areItemsTheSame(oldItem: Category, newItem: Category) = newItem == oldItem

    override fun areContentsTheSame(oldItem: Category, newItem: Category) = newItem.title == oldItem.title

}