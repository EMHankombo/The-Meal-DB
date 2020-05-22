package com.example.themealdb.di.recipes

import com.example.themealdb.di.app.AppComponent
import com.example.themealdb.di.scope.ActivityScope
import com.example.themealdb.ui.recipes.RecipesActivity
import dagger.Component

@ActivityScope
@Component(modules = [RecipesModule::class], dependencies = [AppComponent::class])
interface RecipesComponent {

    fun inject(recipesActivity: RecipesActivity)
}