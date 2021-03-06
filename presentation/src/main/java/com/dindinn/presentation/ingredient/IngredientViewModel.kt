package com.dindinn.presentation.ingredient

import androidx.lifecycle.MutableLiveData
import com.dindinn.domain.model.FoodModel
import com.dindinn.domain.model.IngredientModel
import com.dindinn.domain.model.TabDetails
import com.dindinn.domain.model.TabModel
import com.dindinn.domain.usecase.FetchIngredientTabsUseCase
import com.dindinn.domain.usecase.FetchIngredientUseCase
import com.dindinn.domain.usecase.SearchUseCase
import com.dindinn.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.FileNotFoundException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val fetchIngredientUseCase: FetchIngredientUseCase,
    private val fetchIngredientTabsUseCase: FetchIngredientTabsUseCase,
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    val ingredientList = MutableLiveData<IngredientModel>()
    val ingredientTabs = MutableLiveData<TabModel>()
    val foodSearchResult = MutableLiveData<List<FoodModel>>()
    val foodSearchList = arrayListOf<FoodModel>()

    val ingredientTabList = arrayListOf<TabDetails>()

    val shouldVisibleEmptyIngredientList = MutableLiveData<Boolean>()

    fun getIngredient(id: Int) {
        fetchIngredientUseCase.setParameters(id)
            .execute(
                onSuccess = {
                    shouldVisibleEmptyIngredientList.value = false
                    ingredientList.value = it
                },
                onError = {
                    if (it is FileNotFoundException) {
                        shouldVisibleEmptyIngredientList.value = true
                    }
                }
            )
    }

    fun getIngredientTabs() {
        fetchIngredientTabsUseCase
            .execute(
                onSuccess = {
                    if (ingredientTabs.value == null) {
                        ingredientTabs.value = it
                    }
                    if (it.tabs.isNotEmpty()) {
                        it.tabs[0].categoryId?.let { categoryId -> getIngredient(categoryId) }
                    }
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }

    fun searchIngredientFoods(searchKeyword: String) {

        searchUseCase
            .execute(
                onSuccess = { foods ->
                    foods.forEach { foodDetails ->
                        if (foodDetails.name?.toLowerCase(Locale.ROOT)?.startsWith(
                                searchKeyword.toLowerCase(
                                    Locale.ROOT
                                )
                            ) == true
                        ) {
                            foodSearchList.add(foodDetails)
                        }
                        foodSearchResult.value = foodSearchList.toSet().toList()
                    }
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }


}