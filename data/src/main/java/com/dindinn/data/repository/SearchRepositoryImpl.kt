package com.dindinn.data.repository

import com.dindinn.data.source.remote.DinDinnService
import com.dindinn.domain.model.FoodModel
import com.dindinn.domain.repository.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(
    private val dinDinnService: DinDinnService
) : SearchRepository {

    override fun getSearchResult(): Single<List<FoodModel>> {
        return dinDinnService.getAllItemForSearch()
    }

}