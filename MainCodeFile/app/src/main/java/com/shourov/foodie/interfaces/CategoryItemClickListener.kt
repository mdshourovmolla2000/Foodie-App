package com.shourov.foodie.interfaces

import com.shourov.foodie.model.CategoryModel

interface CategoryItemClickListener {
    fun onClickCategoryItem(currentItem: CategoryModel)
}