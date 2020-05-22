package com.example.themealdb.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themealdb.model.recipes.Meal
import com.example.themealdb.model.recipes.RecipesResponseModel
import com.example.themealdb.testrule.RxImmediateSchedulerRule
import com.example.themealdb.ui.recipes.repository.RecipesRepository
import com.example.themealdb.ui.recipes.viewmodel.RecipesViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var rxRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var recipesRepository: RecipesRepository

    @Mock
    private lateinit var recipesObserver: Observer<RecipesViewModel.RecipesState>

    private lateinit var recipesViewModel: RecipesViewModel

    private val meal = Meal(MEAL_ID, MEAL_NAME, MEAL_IMG)

    private val recipesResponseModel = RecipesResponseModel(listOf(meal))

    @Before
    fun setup() {
        recipesViewModel = RecipesViewModel(recipesRepository)

        recipesViewModel.recipesObservable.observeForever(recipesObserver)
    }

    @Test
    fun `show success state when recipes api returns successfully`() {

        `when`(recipesRepository.getRecipes(CATEGORY)).thenReturn(Single.just(recipesResponseModel))

        recipesViewModel.loadRecipes(CATEGORY)

        verify(recipesObserver).onChanged(RecipesViewModel.RecipesState.Progress)
        verify(recipesObserver).onChanged(RecipesViewModel.RecipesState.Success(recipesResponseModel))
    }

    @Test
    fun `show error state when recipes api returns unsuccessfully`() {
        `when`(recipesRepository.getRecipes(CATEGORY)).thenReturn(Single.error(RuntimeException()))

        recipesViewModel.loadRecipes(CATEGORY)

        verify(recipesObserver).onChanged(RecipesViewModel.RecipesState.Progress)
        verify(recipesObserver).onChanged(RecipesViewModel.RecipesState.Error)
    }

    companion object {
        const val MEAL_ID = "Id"
        const val MEAL_NAME = "Meal name"
        const val MEAL_IMG = "Meal.png"

        const val CATEGORY = "Category"
    }
}