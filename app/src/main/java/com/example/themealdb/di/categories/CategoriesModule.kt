package com.example.themealdb.di.categories

import androidx.lifecycle.ViewModelProvider
import com.example.themealdb.ui.categories.CategoriesActivity
import com.example.themealdb.di.scope.ActivityScope
import com.example.themealdb.ui.categories.repository.CategoryRepository
import com.example.themealdb.ui.categories.viewmodel.CategoriesViewModel
import com.example.themealdb.ui.categories.viewmodel.CategoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule(private val categoriesActivity: CategoriesActivity) {

    @Provides
    @ActivityScope
    fun provideViewModelFactory(categoryRepository: CategoryRepository): CategoryViewModelFactory {
        return CategoryViewModelFactory(categoryRepository)
    }

    @Provides
    @ActivityScope
    fun provideCategoriesViewModel(categoryViewModelFactory: CategoryViewModelFactory): CategoriesViewModel {
        return ViewModelProvider(categoriesActivity, categoryViewModelFactory).get(
            CategoriesViewModel::class.java
        )
    }
}