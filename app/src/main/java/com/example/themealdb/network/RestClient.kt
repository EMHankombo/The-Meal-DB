package com.example.themealdb.network

import com.example.themealdb.common.CATEGORIES_ENDPOINT
import com.example.themealdb.common.RECIPES_ENDPOINT
import com.example.themealdb.common.RECIPE_QUERY_KEY
import com.example.themealdb.model.categories.CategoriesResponseModel
import com.example.themealdb.model.recipes.RecipesResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestClient {

    @GET(CATEGORIES_ENDPOINT)
    fun getAllCategories(): Single<CategoriesResponseModel>

    @GET(RECIPES_ENDPOINT)
    fun getRecipes(@Query(RECIPE_QUERY_KEY) category: String): Single<RecipesResponseModel>
}