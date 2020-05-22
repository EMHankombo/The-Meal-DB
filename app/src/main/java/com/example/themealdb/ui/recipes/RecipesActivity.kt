package com.example.themealdb.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealdb.MyApp
import com.example.themealdb.R
import com.example.themealdb.di.recipes.DaggerRecipesComponent
import com.example.themealdb.di.recipes.RecipesModule
import com.example.themealdb.model.recipes.Meal
import com.example.themealdb.ui.categories.CategoriesActivity.Companion.CATEGORY_KEY
import com.example.themealdb.ui.recipes.adapter.RecipesAdapter
import com.example.themealdb.ui.recipes.viewmodel.RecipesViewModel
import kotlinx.android.synthetic.main.activity_recipes.*
import kotlinx.android.synthetic.main.error.*
import javax.inject.Inject

class RecipesActivity : AppCompatActivity() {

    @Inject
    lateinit var recipesViewModel: RecipesViewModel
    private lateinit var category: String
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        initDagger()

        setupRecyclerView()


        if (intent != null) {
            category = intent.getStringExtra(CATEGORY_KEY)
        }

        recipesViewModel.loadRecipes(category)

        setupObservers()


        btn_retry.setOnClickListener {
            recipesViewModel.loadRecipes(category)
        }

    }

    private fun setupRecyclerView() {
         recipesAdapter = RecipesAdapter(mutableListOf())
        rv_recipes.apply {
            layoutManager = LinearLayoutManager(this@RecipesActivity)
            adapter = recipesAdapter
        }
    }

    private fun setupObservers() {
        recipesViewModel.recipesObservable.observe(this, Observer {
            when (it) {
                is RecipesViewModel.RecipesState.Progress -> showProgress()
                is RecipesViewModel.RecipesState.Error -> handleError()
                is RecipesViewModel.RecipesState.Success -> handleSuccess(it.recipesResponseModel.meals)
            }
        })
    }

    private fun handleSuccess(meals: List<Meal>) {
        progress_container_recipes.visibility = View.GONE
        error_container_recipes.visibility = View.GONE
        rv_recipes.visibility = View.VISIBLE
        recipesAdapter.setMealsData(meals)
    }

    private fun showProgress() {
        error_container_recipes.visibility = View.GONE
        rv_recipes.visibility = View.GONE
        progress_container_recipes.visibility = View.VISIBLE
    }

    private fun handleError() {
        progress_container_recipes.visibility = View.GONE
        rv_recipes.visibility = View.GONE
        error_container_recipes.visibility = View.VISIBLE
    }

    private fun initDagger() {
        DaggerRecipesComponent.builder().appComponent((application as MyApp).component())
            .recipesModule(
                RecipesModule(this)
            ).build().inject(this)
    }
}
