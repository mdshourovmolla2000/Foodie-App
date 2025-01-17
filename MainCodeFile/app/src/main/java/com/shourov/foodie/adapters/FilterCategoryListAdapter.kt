package com.shourov.foodie.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shourov.foodie.R
import com.shourov.foodie.databinding.SingleFilterCategoryItemLayoutBinding
import com.shourov.foodie.interfaces.CategoryItemClickListener
import com.shourov.foodie.model.CategoryModel

class FilterCategoryListAdapter(private var itemList: MutableList<CategoryModel>, private var selectedPosition: Int, private val itemClickListener: CategoryItemClickListener):
    RecyclerView.Adapter<FilterCategoryListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_filter_category_item_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.onBind(itemList[position], position)

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SingleFilterCategoryItemLayoutBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun onBind(currentItem: CategoryModel, position: Int) {
            binding.apply {
                categoryNameTextview.text = currentItem.categoryName

                //selecting bg color of current selected item
                if (selectedPosition == position) {
                    itemCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.themeColor))
                    categoryNameTextview.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteColor))
                } else {
                    itemCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.categoryBackgroundColor))
                    categoryNameTextview.setTextColor(ContextCompat.getColor(itemView.context, R.color.grayColor))
                }

                itemCardView.setOnClickListener {
                    if (selectedPosition != position) {
                        val previousIndex = selectedPosition
                        selectedPosition = position
                        itemClickListener.onClickCategoryItem(currentItem)

                        notifyItemChanged(previousIndex)
                        notifyItemChanged(selectedPosition)
                    }
                }
            }
        }
    }
}