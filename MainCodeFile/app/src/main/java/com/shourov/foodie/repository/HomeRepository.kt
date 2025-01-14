package com.shourov.foodie.repository

import com.shourov.foodie.data.CategoryData
import com.shourov.foodie.data.FoodData
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import java.io.IOException
import java.net.SocketException

class HomeRepository {
    fun getCategoryData(callback: (data: List<CategoryModel>?, message: String?) -> Unit) {
        try {
            val response = CategoryData().getData()

            callback(response, "Successful")
        }
        catch (e: IOException) { callback(null, "Network error") }
        catch (e: SocketException) { callback(null, "Network error") }
        catch (e: Exception) { callback(null, "Something wrong") }
    }

    fun getFoodData(categoryName: String, callback: (data: List<FoodModel>?, message: String?) -> Unit) {
        try {
            val response = if (categoryName == "All") {
                FoodData().getData().asReversed()
            } else {
                FoodData().getData().asReversed().filter { it.itemCategory == categoryName }
            }

            callback(response, "Successful")
        }
        catch (e: IOException) { callback(null, "Network error") }
        catch (e: SocketException) { callback(null, "Network error") }
        catch (e: Exception) { callback(null, "Something wrong") }
    }
}