package com.shourov.foodie.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shourov.foodie.R
import com.shourov.foodie.adapters.FilterCategoryListAdapter
import com.shourov.foodie.adapters.FoodListAdapter
import com.shourov.foodie.common.BaseFragment
import com.shourov.foodie.databinding.BottomSheetSearchFilterBinding
import com.shourov.foodie.databinding.FragmentSearchBinding
import com.shourov.foodie.interfaces.CategoryItemClickListener
import com.shourov.foodie.interfaces.FoodItemClickListener
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import com.shourov.foodie.repository.SearchRepository
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showErrorToast
import com.shourov.foodie.utils.showSuccessToast
import com.shourov.foodie.view_model.SearchViewModel

class SearchFragment : BaseFragment(), FoodItemClickListener, CategoryItemClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(SearchRepository()) }

    private val foodListAdapter by lazy { FoodListAdapter(mutableListOf(), this) }

    private var foodName = ""
    private var selectedCategory = "All"
    private var minPrice = 0.0
    private var maxPrice = 100.0

    private var tempSelectedCategory = "All"
    private var tempMinPrice = 0.0
    private var tempMaxPrice = 100.0

    private var selectedCategoryPosition: Int = 0
    private val categoryList = mutableListOf<CategoryModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTitle("Search Food")

        getCategoryList()

        binding.apply {
            foodListRecyclerview.adapter = foodListAdapter

            searchEdittext.doOnTextChanged { text, _, _, _ ->
                foodName = text.toString().trim()
                foodCounterTextview.visibility = if (foodName.isEmpty()) View.GONE else View.VISIBLE
                getFoodData()
            }

            filterButton.setOnClickListener { openFilterBottomSheet() }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getFoodData() {
        viewModel.getFoodData(foodName, selectedCategory, minPrice, maxPrice) { data, message ->
            binding.apply {
                when(message) {
                    "Something wrong" -> requireContext().showErrorToast(message)
                    "Network error" -> requireContext().showErrorToast(message)
                    "Successful" -> {
                        binding.foodCounterTextview.text = "Found\n${data?.size ?: 0} results"
                        foodListAdapter.submitList(data!!)
                    }
                }
            }
        }
    }

    private fun getCategoryList() {
        viewModel.getCategoryData { data, message ->
            when(message) {
                "Something wrong" -> {
                    requireContext().showErrorToast(message)
                }
                "Network error" -> {
                    requireContext().showErrorToast(message)
                }
                "Successful" -> {
                    categoryList.clear()
                    categoryList.addAll(data!!)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openFilterBottomSheet() {
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetSearchFilterBinding.inflate(layoutInflater)

        bottomSheet.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.apply {
            priceRangeSlider.values = listOf(minPrice.toFloat(), maxPrice.toFloat())
            priceRangeTextview.text = "$$minPrice - $$maxPrice"

            val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
                alignItems = AlignItems.FLEX_START
            }

            filterCategoryListRecyclerview.layoutManager = flexboxLayoutManager

            val filterCategoryListAdapter = FilterCategoryListAdapter(categoryList, selectedCategoryPosition, this@SearchFragment)
            filterCategoryListRecyclerview.adapter = filterCategoryListAdapter

            priceRangeSlider.addOnChangeListener { slider, _, _ ->
                tempMinPrice = slider.values[0].toDouble()
                tempMaxPrice = slider.values[1].toDouble()

                priceRangeTextview.text = "$$tempMinPrice - $$tempMaxPrice"
            }

            clearAllButton.setOnClickListener {
                selectedCategoryPosition = 0
                tempSelectedCategory = "All"
                tempMinPrice = 0.0
                tempMaxPrice = 100.0

                selectedCategory = tempSelectedCategory
                minPrice = tempMinPrice
                maxPrice = tempMaxPrice

                getFoodData()
                bottomSheet.dismiss()
                requireContext().showSuccessToast("All filter cleared")
            }

            applyButton.setOnClickListener {
                selectedCategory = tempSelectedCategory
                minPrice = tempMinPrice
                maxPrice = tempMaxPrice

                getFoodData()
                bottomSheet.dismiss()
            }
        }

        bottomSheet.show()
    }

    override fun onClickFoodItem(currentItem: FoodModel) {
        val bundle = bundleOf(
            "PRODUCT_ID" to currentItem.id
        )
        NavigationHelper.navigateTo(findNavController(), R.id.action_searchFragment_to_foodDetailsFragment, bundle)
    }

    override fun onClickCategoryItem(currentItem: CategoryModel) {
        tempSelectedCategory = currentItem.categoryName
        selectedCategoryPosition = categoryList.indexOf(currentItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


class SearchViewModelFactory(private val repository: SearchRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SearchViewModel(repository) as T
}