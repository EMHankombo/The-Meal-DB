package com.example.themealdb.ui.recipes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themealdb.model.recipes.RecipesResponseModel
import com.example.themealdb.ui.recipes.repository.RecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipesViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _recipesObservable = MutableLiveData<RecipesState>()
    val recipesObservable: LiveData<RecipesState>
        get() = _recipesObservable

    fun loadRecipes(category: String) {

        _recipesObservable.value = RecipesViewModel.RecipesState.Progress

        compositeDisposable.add(
            recipesRepository.getRecipes(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ recipes ->
                    _recipesObservable.value = RecipesViewModel.RecipesState.Success(recipes)
                }, {
                    _recipesObservable.value = RecipesViewModel.RecipesState.Error

                })
        )
    }

    sealed class RecipesState {
        object Progress : RecipesState()
        object Error : RecipesState()
        data class Success(val recipesResponseModel: RecipesResponseModel) : RecipesState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}