package com.dindinn.app.data.repository

import android.content.Context
import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.repository.IngredientRepository
import com.dindinn.app.util.Utils
import io.reactivex.Single

class IngredientRepositoryImpl(
    private val context: Context
) : IngredientRepository {

    override fun getIngredientListById(id: Int): Single<IngredientModel> {
        return Utils.getLocalIngredient(context, id)
    }
}