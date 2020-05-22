package com.example.themealdb.ui.recipes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themealdb.ui.recipes.repository.RecipesRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RecipesViewModelFactory @Inject constructor(private val recipesRepository: RecipesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesViewModel(recipesRepository) as T
    }
}