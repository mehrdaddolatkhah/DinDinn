package com.dindinn.app.data.repository

import com.dindinn.app.data.source.remote.DinDinnService
import com.dindinn.app.domain.model.FoodModel
import com.dindinn.app.domain.repository.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(
    private val dinDinnService: DinDinnService
) : SearchRepository {

    override fun getSearchResult(): Single<List<FoodModel>> {
        return dinDinnService.getAllItemForSearch()
    }

}