package com.example.shoppinglist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1,)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao

    companion object{
        private val instance : ShoppingDatabase?= null

        operator fun invoke(context: Context){
            getInstance(context)
        }
        fun getInstance(context:Context) : ShoppingDatabase {
            return instance
                ?: Room.databaseBuilder(
                    context,
                    ShoppingDatabase::class.java, name = "shopping_item_db"
                ).build()
        }
    }
}