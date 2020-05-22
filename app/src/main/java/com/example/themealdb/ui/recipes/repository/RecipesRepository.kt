package com.example.themealdb.ui.recipes.repository

import com.example.themealdb.model.recipes.RecipesResponseModel
import io.reactivex.Single

interface RecipesRepository {

    fun getRecipes(category: String): Single<RecipesResponseModel>
}