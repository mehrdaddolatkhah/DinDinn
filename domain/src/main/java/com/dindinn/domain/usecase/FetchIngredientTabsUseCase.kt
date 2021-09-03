package com.dindinn.domain.usecase

import com.dindinn.domain.model.TabModel
import com.dindinn.domain.repository.IngredientRepository
import com.dindinn.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchIngredientTabsUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : SingleUseCase<TabModel>() {

    override fun buildUseCaseSingle(): Single<TabModel> {
        return ingredientRepository.getIngredientTabs()
    }
}