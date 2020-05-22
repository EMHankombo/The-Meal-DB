package com.example.themealdb

import android.app.Application
import com.example.themealdb.di.app.AppComponent
import com.example.themealdb.di.app.DaggerAppComponent

class MyApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }

    fun component() = appComponent
}