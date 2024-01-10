package com.example.shoppinglist.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.shoppinglist.ShoppingApplication
import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingItemViewModel @Inject constructor(val shoppingRepository: ShoppingRepository) : ViewModel() {

    fun insertItem(shoppingItem: ShoppingItem) = shoppingRepository.insertItem(shoppingItem)

    suspend fun deleteItem(shoppingItem: ShoppingItem) = shoppingRepository.deleteItem(shoppingItem)

    suspend fun getAllItems() = shoppingRepository.getAllItems()



    class Factory(private val repo: ShoppingRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingItemViewModel(repo) as T
        }
    }

}