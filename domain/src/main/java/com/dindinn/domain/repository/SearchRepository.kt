package com.dindinn.domain.repository

import com.dindinn.domain.model.FoodModel
import io.reactivex.Single

interface SearchRepository {

    fun getSearchResult(): Single<List<FoodModel>>
}