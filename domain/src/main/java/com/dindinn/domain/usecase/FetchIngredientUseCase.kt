package com.dindinn.domain.usecase

import com.dindinn.domain.model.IngredientModel
import com.dindinn.domain.repository.IngredientRepository
import com.dindinn.domain.usecase.base.SingleUseCase
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