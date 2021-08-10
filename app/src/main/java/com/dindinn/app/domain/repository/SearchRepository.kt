package com.dindinn.app.domain.repository

import com.dindinn.app.domain.model.FoodModel
import io.reactivex.Single

interface SearchRepository {

    fun getSearchResult(): Single<List<FoodModel>>
}