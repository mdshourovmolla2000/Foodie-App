package com.shourov.foodie.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import com.shourov.foodie.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    fun getCategoryData(callback: (data: MutableList<CategoryModel>?, message: String?) -> Unit) = viewModelScope.launch { repository.getCategoryData(callback) }

    fun getFoodData(categoryName: String, callback: (data: MutableList<FoodModel>?, message: String?) -> Unit) = viewModelScope.launch { repository.getFoodData(categoryName, callback) }
}