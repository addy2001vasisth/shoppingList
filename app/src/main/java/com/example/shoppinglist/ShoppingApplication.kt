package com.example.shoppinglist

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){

    private var appContext : Context ?= null

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    fun getContext() : Context{
        appContext = this
        return appContext!!
    }

}