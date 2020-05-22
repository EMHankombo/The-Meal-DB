package com.example.themealdb.di.recipes

import androidx.lifecycle.ViewModelProvider
import com.example.themealdb.di.scope.ActivityScope
import com.example.themealdb.ui.recipes.RecipesActivity
import com.example.themealdb.ui.recipes.repository.RecipesRepository
import com.example.themealdb.ui.recipes.viewmodel.RecipesViewModel
import com.example.themealdb.ui.recipes.viewmodel.RecipesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RecipesModule(private val recipesActivity: RecipesActivity) {

    @Provides
    @ActivityScope
    fun provideRecipesViewModelFactory(repository: RecipesRepository): RecipesViewModelFactory {
        return RecipesViewModelFactory(repository)
    }

    @Provides
    @ActivityScope
    fun provideRecipesViewModel(viewModelFactory: RecipesViewModelFactory): RecipesViewModel {
        return ViewModelProvider(
            recipesActivity,
            viewModelFactory
        ).get(RecipesViewModel::class.java)
    }
}