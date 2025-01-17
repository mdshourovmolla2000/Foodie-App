package com.shourov.foodie.repository

import com.shourov.foodie.R
import com.shourov.foodie.data.CategoryData
import com.shourov.foodie.data.FoodData
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import java.io.IOException
import java.net.SocketException

class SearchRepository {

    fun getCategoryData(callback: (data: MutableList<CategoryModel>?, message: String?) -> Unit) {
        try {
            val response = CategoryData().getData()
            response.add(0, CategoryModel(R.drawable.category_all_icon,"All"))

            callback(response, "Successful")
        }
        catch (e: IOException) { callback(null, "Network error") }
        catch (e: SocketException) { callback(null, "Network error") }
        catch (e: Exception) { callback(null, "Something wrong") }
    }

    fun getFoodData(foodName: String, categoryName: String, minPrice: Double, maxPrice: Double, callback: (data: MutableList<FoodModel>?, message: String?) -> Unit) {
        try {
            if (foodName.isBlank()) {
                callback(mutableListOf(), "Successful")
                return
            }

            val allData = FoodData().getData()
            val filteredData = allData.filter { foodItem ->
                (categoryName == "All" || foodItem.itemCategory.equals(categoryName, ignoreCase = true)) &&
                        ((foodItem.itemPrice ?: 0.0) in minPrice..maxPrice) &&
                        (foodItem.itemName?.contains(foodName, ignoreCase = true) == true)
            }.asReversed().toMutableList()

            callback(filteredData, "Successful")
        }
        catch (e: IOException) { callback(null, "Network error") }
        catch (e: SocketException) { callback(null, "Network error") }
        catch (e: Exception) { callback(null, "Something wrong") }
    }
}