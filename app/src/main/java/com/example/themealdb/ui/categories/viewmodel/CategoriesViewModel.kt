package com.example.themealdb.ui.categories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themealdb.model.categories.CategoriesResponseModel
import com.example.themealdb.ui.categories.repository.CategoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _categoryObservable = MutableLiveData<CategoryState>()
    val categoryObservable: LiveData<CategoryState>
        get() = _categoryObservable

    fun loadCategories() {
        _categoryObservable.value = CategoryState.Progress

        compositeDisposable.add(
            categoryRepository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ categories ->
                    _categoryObservable.value = CategoryState.Success(categories)
                }, {
                    _categoryObservable.value = CategoryState.Error

                })
        )
    }

    sealed class CategoryState {
        object Progress : CategoryState()
        object Error : CategoryState()
        data class Success(val categoriesResponseModel: CategoriesResponseModel) : CategoryState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}