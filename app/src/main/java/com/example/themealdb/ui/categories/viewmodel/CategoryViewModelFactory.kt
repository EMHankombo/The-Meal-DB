package com.example.themealdb.ui.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themealdb.ui.categories.repository.CategoryRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoryRepository) as T
    }
}