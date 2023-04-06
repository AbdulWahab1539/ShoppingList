package com.abduldev.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abduldev.shoppinglist.data.repo.ShoppingRepo

class ShoppingViewModelFactory(
    private val repo: ShoppingRepo,
) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repo) as T
    }
}