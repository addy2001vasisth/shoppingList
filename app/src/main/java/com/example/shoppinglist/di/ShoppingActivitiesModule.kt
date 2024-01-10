package com.example.shoppinglist.di

import android.app.Activity
import android.view.LayoutInflater
import com.example.shoppinglist.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
object ShoppingActivityModule {

    @Qualifier
    internal annotation class ActivityLayoutInflator


    @Provides
    @ActivityLayoutInflator
    fun layoutInflater(activity: Activity): LayoutInflater {
        return activity.layoutInflater
    }


    @Provides
    @ActivityScoped
    fun provideShoppingActivityBinding(@ActivityLayoutInflator layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}