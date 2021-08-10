package com.dindinn.app.domain.usecase

import com.dindinn.app.domain.model.TabModel
import com.dindinn.app.domain.repository.IngredientRepository
import com.dindinn.app.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchIngredientTabsUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : SingleUseCase<TabModel>() {

    override fun buildUseCaseSingle(): Single<TabModel> {
        return ingredientRepository.getIngredientTabs()
    }
}