package com.dindinn.app.data.repository

import com.dindinn.app.data.source.remote.DinDinnService
import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.model.TabModel
import com.dindinn.app.domain.repository.IngredientRepository
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