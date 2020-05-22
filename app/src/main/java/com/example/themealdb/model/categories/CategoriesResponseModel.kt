package com.example.themealdb.model.categories


import com.google.gson.annotations.SerializedName

data class CategoriesResponseModel(
    @SerializedName("categories")
    val categories: List<Category>
)