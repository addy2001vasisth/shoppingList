package com.example.shoppinglist.di

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.databinding.FragmentAddingItemBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
//
//@Module
//@InstallIn(FragmentComponent::class)
//object ShoppingFragmentModule {
//
//    @Qualifier
//    internal annotation class FragmentLayoutInflator
//
//    @Qualifier
//    internal annotation class FragmentViewGroup
//
//    @Provides
//    @FragmentLayoutInflator
//    fun layoutInflater(fragment: Fragment): LayoutInflater {
//        return fragment.layoutInflater
//    }
//
//    @Provides
//    @FragmentViewGroup
//    fun provideFragmentViewGroup(fragment: Fragment) : ViewGroup {
//        return fragment.view
//    }
//
//
//
//
//
//    @Provides
//    @FragmentScoped
//    fun provideShoppingActivityBinding(@FragmentLayoutInflator layoutInflater: LayoutInflater): FragmentAddingItemBinding {
//        return FragmentAddingItemBinding.inflate(layoutInflater,)
//    }
//
//}