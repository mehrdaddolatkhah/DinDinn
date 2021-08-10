package com.dindinn.app.domain.usecase

import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.repository.IngredientRepository
import com.dindinn.app.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchIngredientUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : SingleUseCase<IngredientModel>() {

    private var ingredientId: Int = 0

    fun setParameters(ingredientId: Int): FetchIngredientUseCase {
        this.ingredientId = ingredientId
        return this
    }

    override fun buildUseCaseSingle(): Single<IngredientModel> {
        return ingredientRepository.getIngredientListById(ingredientId)
    }
}