package com.shourov.foodie.adapters

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shourov.foodie.R
import com.shourov.foodie.databinding.SingleCategoryItemLayoutBinding
import com.shourov.foodie.interfaces.CategoryItemClickListener
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.utils.loadImage

class CategoryListAdapter(private val itemList: ArrayList<CategoryModel>, currentCategoryPosition: Int, private val itemClickListener: CategoryItemClickListener):
    RecyclerView.Adapter<CategoryListAdapter.ItemViewHolder>() {

    private var currentIndex = currentCategoryPosition

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_category_item_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.onBind(itemList[position], position)

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SingleCategoryItemLayoutBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun onBind(currentItem: CategoryModel, position: Int) {
            binding.apply {
                categoryIconImageview.loadImage(currentItem.categoryImage)
                categoryNameTextview.text = currentItem.categoryName

                //selecting bg color of current selected item
                if (currentIndex == position) {
                    itemCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.themeColor))
                    categoryIconImageview.setColorFilter(ContextCompat.getColor(itemView.context, R.color.whiteColor), PorterDuff.Mode.SRC_IN)
                    categoryNameTextview.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteColor))
                } else {
                    itemCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.categoryBackgroundColor))
                    categoryIconImageview.setColorFilter(ContextCompat.getColor(itemView.context, R.color.grayColor), PorterDuff.Mode.SRC_IN)
                    categoryNameTextview.setTextColor(ContextCompat.getColor(itemView.context, R.color.grayColor))
                }
            }

            itemView.setOnClickListener {
                currentIndex = position
                itemClickListener.onClickCategoryItem(currentItem.categoryName, position)
                notifyDataSetChanged()
            }
        }
    }
}