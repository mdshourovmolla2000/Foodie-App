package com.shourov.foodie.data

import com.shourov.foodie.R
import com.shourov.foodie.model.CategoryModel

class CategoryData {
    fun getData(): MutableList<CategoryModel> {
        val itemList: MutableList<CategoryModel> = mutableListOf()
        itemList.add(CategoryModel(R.drawable.category_all_icon,"All"))
        itemList.add(CategoryModel(R.drawable.category_fast_food_icon,"Fast food"))
        itemList.add(CategoryModel(R.drawable.category_drinks_icon,"Drinks"))
        itemList.add(CategoryModel(R.drawable.category_snacks_icon,"Snacks"))
        return itemList
    }
}