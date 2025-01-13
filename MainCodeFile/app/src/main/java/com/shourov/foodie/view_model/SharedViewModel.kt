package com.shourov.foodie.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _titleLiveData = MutableLiveData<String?>()
    val titleLiveData: LiveData<String?> get() = _titleLiveData
    fun setTitle(title: String?) { _titleLiveData.value = title }
}