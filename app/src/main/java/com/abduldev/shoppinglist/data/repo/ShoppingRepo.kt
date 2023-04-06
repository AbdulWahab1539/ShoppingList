package com.abduldev.shoppinglist.data.repo

import com.abduldev.shoppinglist.data.db.ShoppingDatabase
import com.abduldev.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepo(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}