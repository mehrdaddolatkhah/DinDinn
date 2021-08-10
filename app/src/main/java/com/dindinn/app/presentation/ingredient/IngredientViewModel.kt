package com.dindinn.app.presentation.ingredient

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.model.TabModel
import com.dindinn.app.domain.usecase.FetchIngredientTabsUseCase
import com.dindinn.app.domain.usecase.FetchIngredientUseCase
import com.dindinn.app.presentation.base.BaseViewModel

class IngredientViewModel @ViewModelInject constructor(
    private val fetchIngredientUseCase: FetchIngredientUseCase,
    private val fetchIngredientTabsUseCase: FetchIngredientTabsUseCase
) : BaseViewModel() {

    val ingredientList = MutableLiveData<IngredientModel>()
    val ingredientTabs = MutableLiveData<TabModel>()

    fun getIngredient(id: Int) {
        fetchIngredientUseCase.setParameters(id)
            .execute(
                onSuccess = {
                    ingredientList.value = it
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }

    fun getIngredientTabs() {
        fetchIngredientTabsUseCase
            .execute(
                onSuccess = {
                    ingredientTabs.value = it
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }


}