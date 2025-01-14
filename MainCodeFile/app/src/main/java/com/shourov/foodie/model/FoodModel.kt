package com.shourov.foodie.model

import com.shourov.foodie.R

data class FoodModel(
    val id: String? = "1",
    val itemImage: Int? = R.drawable.loading_image,
    val itemName: String? = "",
    val itemCategory: String? = "",
    val itemDescription: String? = "",
    val itemDeliveryTime: String? = "",
    val itemPrice: Double? = 0.0,
    val itemRating: Double? = 0.0
)