package com.dindinn.domain.repository

import com.dindinn.domain.model.IngredientModel
import com.dindinn.domain.model.TabModel
import io.reactivex.Single

interface IngredientRepository {

    fun getIngredientListById(id: Int): Single<IngredientModel>

    fun getIngredientTabs(): Single<TabModel>
}