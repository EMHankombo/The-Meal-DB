package com.example.themealdb.ui.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealdb.MyApp
import com.example.themealdb.R
import com.example.themealdb.di.categories.CategoriesModule
import com.example.themealdb.di.categories.DaggerCategoriesComponent
import com.example.themealdb.model.categories.Category
import com.example.themealdb.ui.categories.adapter.CategoriesAdapter
import com.example.themealdb.ui.categories.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.error.*
import javax.inject.Inject

class CategoriesActivity : AppCompatActivity() {

    @Inject
    lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        initDagger()

        categoriesViewModel.loadCategories()

        setupObservers()


        setupRecyclerView()

        btn_retry.setOnClickListener {
            categoriesViewModel.loadCategories()
        }
    }

    private fun setupRecyclerView() {
        categoriesAdapter =
            CategoriesAdapter(mutableListOf()) { category: String -> handleClicks(category) }
        rv_categories.apply {
            layoutManager = LinearLayoutManager(this@CategoriesActivity)
            adapter = categoriesAdapter
        }
    }

    private fun setupObservers() {
        categoriesViewModel.categoryObservable.observe(this, Observer {
            when (it) {
                is CategoriesViewModel.CategoryState.Progress -> showProgress()
                is CategoriesViewModel.CategoryState.Error -> handleError()
                is CategoriesViewModel.CategoryState.Success -> handleSuccess(it.categoriesResponseModel.categories)
            }
        })
    }

    private fun handleSuccess(categories: List<Category>) {
        progress_container.visibility = View.GONE
        error_container.visibility = View.GONE
        rv_categories.visibility = View.VISIBLE

        categoriesAdapter.setCategoriesData(categories.toMutableList())
    }

    private fun showProgress() {
        rv_categories.visibility = View.GONE
        error_container.visibility = View.GONE
        progress_container.visibility = View.VISIBLE
    }

    private fun handleError() {
        progress_container.visibility = View.GONE
        rv_categories.visibility = View.GONE
        error_container.visibility = View.VISIBLE

    }

    private fun handleClicks(category: String) {

        Toast.makeText(this, category, Toast.LENGTH_SHORT).show()
    }

    private fun initDagger() {
        DaggerCategoriesComponent.builder().appComponent((application as MyApp).component())
            .categoriesModule(
                CategoriesModule(this)
            ).build().inject(this)
    }
}
