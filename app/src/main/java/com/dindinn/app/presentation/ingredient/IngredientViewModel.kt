package com.dindinn.app.presentation.ingredient

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.model.TabDetails
import com.dindinn.app.domain.model.TabModel
import com.dindinn.app.domain.usecase.FetchIngredientTabsUseCase
import com.dindinn.app.domain.usecase.FetchIngredientUseCase
import com.dindinn.app.presentation.base.BaseViewModel
import java.io.FileNotFoundException

class IngredientViewModel @ViewModelInject constructor(
    private val fetchIngredientUseCase: FetchIngredientUseCase,
    private val fetchIngredientTabsUseCase: FetchIngredientTabsUseCase
) : BaseViewModel() {

    val ingredientList = MutableLiveData<IngredientModel>()
    val ingredientTabs = MutableLiveData<TabModel>()

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
                    ingredientTabs.value = it
                    if (it.tabs.isNotEmpty()) {
                        it.tabs[0].categoryId?.let { categoryId -> getIngredient(categoryId) }
                    }
                },
                onError = {
                    it.printStackTrace()
                }
            )
    }


}