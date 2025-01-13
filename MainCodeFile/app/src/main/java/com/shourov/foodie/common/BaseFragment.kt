package com.shourov.foodie.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.shourov.foodie.view_model.SharedViewModel

open class BaseFragment: Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    protected fun updateTitle(title: String) {
        sharedViewModel.setTitle(title)
    }
}