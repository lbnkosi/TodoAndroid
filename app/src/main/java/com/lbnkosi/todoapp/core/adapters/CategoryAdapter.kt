package com.lbnkosi.todoapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lbnkosi.todoapp.core.mappers.display.CategoryDisplayMapper
import com.lbnkosi.todoapp.core.models.Category
import com.lbnkosi.todoapp.core.util.recyclerview.DataBoundListAdapter
import com.lbnkosi.todoapp.databinding.CategoryProgressRowBinding

class CategoryAdapter : DataBoundListAdapter<Category, CategoryProgressRowBinding>() {

    override fun createBinding(parent: ViewGroup?) = CategoryProgressRowBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

    override fun bind(binding: CategoryProgressRowBinding, item: Category) {
        binding.categoryDisplay = CategoryDisplayMapper().toCategoryDisplay(item)
    }

}