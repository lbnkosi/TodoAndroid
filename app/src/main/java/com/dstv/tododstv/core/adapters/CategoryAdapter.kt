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
        binding.categoryDisplay = CategoryDisplayMapper().toCategoryDisplay(item)
    }

}