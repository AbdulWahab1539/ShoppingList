package com.abduldev.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.abduldev.shoppinglist.data.db.entities.ShoppingItem
import com.abduldev.shoppinglist.data.repo.ShoppingRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repo: ShoppingRepo
) : ViewModel() {

    fun upsert(item: ShoppingItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repo.upsert(item)
        }

    fun delete(item: ShoppingItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repo.delete(item)
        }

    fun getAllShoppingItems() =
        repo.getAllShoppingItems()


}