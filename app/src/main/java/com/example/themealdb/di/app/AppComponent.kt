package com.example.themealdb.di.app

import com.example.themealdb.MyApp
import com.example.themealdb.ui.categories.repository.CategoryRepository
import com.example.themealdb.ui.recipes.repository.RecipesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(myApp: MyApp)

    fun categoriesRepository(): CategoryRepository

    fun recipesRepository(): RecipesRepository
}