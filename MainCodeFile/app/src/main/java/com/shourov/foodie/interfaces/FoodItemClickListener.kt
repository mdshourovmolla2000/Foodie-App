package com.shourov.foodie.interfaces

import com.shourov.foodie.model.FoodModel

interface FoodItemClickListener {
    fun onClickFoodItem(currentItem: FoodModel)
}