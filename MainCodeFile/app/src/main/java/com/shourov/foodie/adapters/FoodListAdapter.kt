package com.shourov.foodie.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shourov.foodie.R
import com.shourov.foodie.databinding.SingleFoodItemLayoutBinding
import com.shourov.foodie.interfaces.FoodItemClickListener
import com.shourov.foodie.model.FoodModel
import com.shourov.foodie.utils.loadImage

class FoodListAdapter(private var itemList: MutableList<FoodModel>, private val listener: FoodItemClickListener):
    RecyclerView.Adapter<FoodListAdapter.ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(itemList: MutableList<FoodModel>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_food_item_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.onBind(itemList[position])

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SingleFoodItemLayoutBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun onBind(currentItem: FoodModel) {
            binding.apply {
                circularImageView.loadImage(currentItem.itemImage)
                itemNameTextview.text = currentItem.itemName
                ratingCountTextview.text = currentItem.itemRating.toString()
                itemPriceTextview.text = "$ ${currentItem.itemPrice}"

                itemCardView.setOnClickListener { listener.onClickFoodItem(currentItem) }
            }
        }
    }
}