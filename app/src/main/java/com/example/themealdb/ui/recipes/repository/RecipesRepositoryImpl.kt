package com.example.themealdb.ui.recipes.repository

import com.example.themealdb.model.recipes.RecipesResponseModel
import com.example.themealdb.network.RestClient
import io.reactivex.Single
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val restClient: RestClient) :
    RecipesRepository {

    override fun getRecipes(category: String): Single<RecipesResponseModel> {
        return restClient.getRecipes(category)
    }
}