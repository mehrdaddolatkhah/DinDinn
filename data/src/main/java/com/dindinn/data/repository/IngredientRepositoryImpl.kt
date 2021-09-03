package com.dindinn.data.repository

import com.dindinn.data.source.remote.DinDinnService
import com.dindinn.domain.model.IngredientModel
import com.dindinn.domain.model.TabModel
import com.dindinn.domain.repository.IngredientRepository
import io.reactivex.Single

class IngredientRepositoryImpl(
    private val dinDinnService: DinDinnService
) : IngredientRepository {

    override fun getIngredientListById(id: Int): Single<IngredientModel> {
        return dinDinnService.getIngredient(id)
    }

    override fun getIngredientTabs(): Single<TabModel> {
        return dinDinnService.getIngredientTabs()
    }
}