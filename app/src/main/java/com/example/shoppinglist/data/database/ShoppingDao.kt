package com.example.shoppinglist.data.database

import androidx.room.*
import com.example.shoppinglist.data.database.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(shoppingItem: ShoppingItem)

    @Delete
    fun deleteItem(shoppingItem: ShoppingItem)

    @Query(value = "select * from shopping_items")
    suspend fun getAllItems() : List<ShoppingItem>
}