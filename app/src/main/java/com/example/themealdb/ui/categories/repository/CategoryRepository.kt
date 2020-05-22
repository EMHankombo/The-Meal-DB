package com.example.themealdb.ui.categories.repository

import com.example.themealdb.model.categories.CategoriesResponseModel
import io.reactivex.Single

interface CategoryRepository {
    fun getCategories(): Single<CategoriesResponseModel>
}