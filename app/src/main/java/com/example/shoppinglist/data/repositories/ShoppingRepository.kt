package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.database.ShoppingDao
import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingRepository(private val db: ShoppingDatabase) : ShoppingDao {

    override fun insertItem(shoppingItem: ShoppingItem) {
        CoroutineScope(Dispatchers.IO).launch {
            db.shoppingDao().insertItem(shoppingItem)
        }
    }

    override fun deleteItem(shoppingItem: ShoppingItem) {
        CoroutineScope(Dispatchers.IO).launch {
            db.shoppingDao().deleteItem(shoppingItem)
        }
    }

    override suspend fun getAllItems(): List<ShoppingItem> {
        val res = CoroutineScope(Dispatchers.IO).async {
            db.shoppingDao().getAllItems()
        }
        return res.await()
    }
}