package com.shourov.foodie.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.adapters.CategoryListAdapter
import com.shourov.foodie.adapters.FoodListAdapter
import com.shourov.foodie.common.BaseFragment
import com.shourov.foodie.databinding.FragmentHomeBinding
import com.shourov.foodie.interfaces.CategoryItemClickListener
import com.shourov.foodie.interfaces.FoodItemClickListener
import com.shourov.foodie.model.CategoryModel
import com.shourov.foodie.model.FoodModel
import com.shourov.foodie.repository.HomeRepository
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showErrorToast
import com.shourov.foodie.view_model.HomeViewModel

class HomeFragment : BaseFragment(), CategoryItemClickListener, FoodItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels { HomeViewModelFactory(HomeRepository()) }

    private var currentCategory = "All"

    private val categoryListAdapter by lazy { CategoryListAdapter(mutableListOf(), this) }
    private val foodListAdapter by lazy { FoodListAdapter(mutableListOf(), this) }

    private var scrollPosition = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        scrollPosition = binding.mainScrollView.scrollY
        outState.putInt("SCROLL_POSITION", scrollPosition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            try {
                scrollPosition = savedInstanceState.getInt("SCROLL_POSITION")
                binding.mainScrollView.post { binding.mainScrollView.scrollTo(0, scrollPosition) }
            } catch (_: Exception) {}
        }
        updateTitle("")

        getCategoryData()

        binding.apply {
            searchCardView.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_homeFragment_to_searchFragment) }
            categoryListRecyclerview.adapter = categoryListAdapter
            foodListRecyclerview.adapter = foodListAdapter
        }
    }

    private fun getCategoryData() {
        viewModel.getCategoryData { data, message ->
            binding.apply {
                when(message) {
                    "Something wrong" -> {
                        requireContext().showErrorToast(message)
                    }
                    "Network error" -> {
                        requireContext().showErrorToast(message)
                    }
                    "Successful" -> {
                        categoryListAdapter.submitList(data!!)
                        getPopularProductData(currentCategory)
                    }
                }
            }
        }
    }

    private fun getPopularProductData(categoryName: String) {
        viewModel.getFoodData(categoryName) { data, message ->
            binding.apply {
                when(message) {
                    "Something wrong" -> requireContext().showErrorToast(message)
                    "Network error" -> requireContext().showErrorToast(message)
                    "Successful" -> {
                        foodListAdapter.submitList(data!!)
                    }
                }
            }
        }
    }

    override fun onClickCategoryItem(currentItem: CategoryModel) {
        currentCategory = currentItem.categoryName
        getPopularProductData(currentCategory)
    }

    override fun onClickFoodItem(currentItem: FoodModel) {
        val bundle = bundleOf(
            "PRODUCT_ID" to currentItem.id
        )
        NavigationHelper.navigateTo(findNavController(), R.id.action_homeFragment_to_foodDetailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




class HomeViewModelFactory(private val repository: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(repository) as T
}