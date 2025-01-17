package com.shourov.foodie.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import com.shourov.foodie.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository): ViewModel() {

    fun getCategoryData(callback: (data: MutableList<CategoryModel>?, message: String?) -> Unit) = viewModelScope.launch { repository.getCategoryData(callback) }

    fun getFoodData(foodName: String, categoryName: String, minPrice: Double, maxPrice: Double, callback: (data: MutableList<FoodModel>?, message: String?) -> Unit) = viewModelScope.launch { repository.getFoodData(foodName, categoryName, minPrice, maxPrice, callback) }
}