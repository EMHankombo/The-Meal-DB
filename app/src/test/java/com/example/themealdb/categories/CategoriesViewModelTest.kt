package com.example.themealdb.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themealdb.model.categories.CategoriesResponseModel
import com.example.themealdb.model.categories.Category
import com.example.themealdb.testrule.RxImmediateSchedulerRule
import com.example.themealdb.ui.categories.repository.CategoryRepository
import com.example.themealdb.ui.categories.viewmodel.CategoriesViewModel
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
class CategoriesViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var rxRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @Mock
    private lateinit var categoriesObserver: Observer<CategoriesViewModel.CategoryState>

    private lateinit var categoriesViewModel: CategoriesViewModel

    private val category = Category(CATEGORY_ID, CATEGORY_NAME, IMG_URL, CATEGORY_DESCRIPTION)
    private val categoriesResponseModel = CategoriesResponseModel(listOf(category))

    @Before
    fun setup() {
        categoriesViewModel = CategoriesViewModel(categoryRepository)

        categoriesViewModel.categoryObservable.observeForever(categoriesObserver)
    }

    @Test
    fun `success state when categories api returns successfully`() {
        `when`(categoryRepository.getCategories()).thenReturn(Single.just(categoriesResponseModel))

        categoriesViewModel.loadCategories()

        verify(categoriesObserver).onChanged(CategoriesViewModel.CategoryState.Progress)
        verify(categoriesObserver).onChanged(
            CategoriesViewModel.CategoryState.Success(
                categoriesResponseModel
            )
        )
    }

    @Test
    fun `error state when categories api returns unsuccessfully`() {
        `when`(categoryRepository.getCategories()).thenReturn(Single.error(RuntimeException()))

        categoriesViewModel.loadCategories()

        verify(categoriesObserver).onChanged(CategoriesViewModel.CategoryState.Progress)
        verify(categoriesObserver).onChanged(
            CategoriesViewModel.CategoryState.Error
        )
    }

    companion object {
        const val CATEGORY_ID = "123"
        const val CATEGORY_NAME = "Cat name"
        const val IMG_URL = "Img1.png"
        const val CATEGORY_DESCRIPTION = "Description"
    }
}