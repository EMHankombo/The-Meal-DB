package com.example.themealdb.di.categories

import com.example.themealdb.di.app.AppComponent
import com.example.themealdb.di.scope.ActivityScope
import com.example.themealdb.ui.categories.CategoriesActivity
import dagger.Component

@ActivityScope
@Component(modules = [CategoriesModule::class], dependencies = [AppComponent::class])
interface CategoriesComponent {
    fun inject(categoriesActivity: CategoriesActivity)
    }