package com.example.themealdb.model.recipes


import com.google.gson.annotations.SerializedName

data class RecipesResponseModel(
    @SerializedName("meals")
    val meals: List<Meal>
)