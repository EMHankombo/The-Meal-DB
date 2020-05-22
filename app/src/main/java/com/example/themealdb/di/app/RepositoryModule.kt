package com.example.themealdb.di.app

import com.example.themealdb.network.RestClient
import com.example.themealdb.ui.categories.repository.CategoryRepository
import com.example.themealdb.ui.categories.repository.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoriesRepository(restClient: RestClient): CategoryRepository {
        return CategoryRepositoryImpl(restClient)
    }
}