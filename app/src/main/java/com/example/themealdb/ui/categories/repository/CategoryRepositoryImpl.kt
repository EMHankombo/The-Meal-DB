package com.example.themealdb.ui.categories.repository

import com.example.themealdb.model.categories.CategoriesResponseModel
import com.example.themealdb.network.RestClient
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val restClient: RestClient) :
    CategoryRepository {

    override fun getCategories(): Single<CategoriesResponseModel> {
        return restClient.getAllCategories()
    }
}