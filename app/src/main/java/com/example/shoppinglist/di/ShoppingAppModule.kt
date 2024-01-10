package com.example.shoppinglist.di

import android.content.Context
import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ShoppingAppModule {

    @Provides
    @Singleton
    fun provideShoppingRepo(db: ShoppingDatabase): ShoppingRepository {
        return ShoppingRepository(db)
    }

    @Provides
    @Singleton
    fun provideShoppingDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return ShoppingDatabase.getInstance(context)
    }
}
