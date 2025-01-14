package com.shourov.foodie.data

import com.shourov.foodie.R
import com.shourov.foodie.model.FoodModel

class FoodData {
    fun getData(): List<FoodModel> {
        val itemList: ArrayList<FoodModel> = ArrayList()
        itemList.add(FoodModel("1", R.drawable.food_pizza_maxicana_image, "Pizza maxicana", "Fast food", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "20 min", 50.00, 4.2))
        itemList.add(FoodModel("2", R.drawable.food_burger_image, "Burger", "Fast food", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 40.00, 4.5))
        itemList.add(FoodModel("3", R.drawable.food_sandwich_image, "Sandwich", "Fast food", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 35.00, 3.8))
        itemList.add(FoodModel("4", R.drawable.food_fried_chicken_image, "Fried chicken", "Fast food", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "25 min", 40.00, 5.0))
        itemList.add(FoodModel("5", R.drawable.food_cocktail_image, "Cocktail", "Drinks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 12.00, 4.3))
        itemList.add(FoodModel("6", R.drawable.food_lemonade_image, "Lemonade", "Drinks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 10.00, 5.0))
        itemList.add(FoodModel("7", R.drawable.food_cappuccino_image, "Cappuccino", "Drinks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 15.00, 3.5))
        itemList.add(FoodModel("8", R.drawable.food_virgin_mojito_image, "Virgin mojito", "Drinks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 10.00, 4.1))
        itemList.add(FoodModel("9", R.drawable.food_popcorn_image, "Popcorn", "Snacks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 5.00, 4.8))
        itemList.add(FoodModel("10", R.drawable.food_chips_image, "Chips", "Snacks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "10 min", 10.00, 3.8))
        itemList.add(FoodModel("11", R.drawable.food_french_fries, "French fries", "Snacks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "20 min", 20.00, 4.5))
        itemList.add(FoodModel("12", R.drawable.food_fish_finger_image, "Fish finger", "Snacks", "Lorem ipsum dolor sit amet . The graphic and typographic operators know this well, in reality all the professions dealing with the universe of communication have a stable relationship with these words, but what is it? Lorem ipsum is a dummy text without any sense.", "20 min", 25.00, 5.0))
        return itemList
    }
}