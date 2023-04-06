package com.abduldev.shoppinglist.ui.shoppinglist

import com.abduldev.shoppinglist.data.db.entities.ShoppingItem

interface DialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}