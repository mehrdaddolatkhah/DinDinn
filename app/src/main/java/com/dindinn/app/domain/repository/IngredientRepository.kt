package com.dindinn.app.domain.repository

import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.model.TabModel
import io.reactivex.Single

interface IngredientRepository {

    fun getIngredientListById(id: Int): Single<IngredientModel>

    fun getIngredientTabs(): Single<TabModel>
}