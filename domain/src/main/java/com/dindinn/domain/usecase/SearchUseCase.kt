package com.dindinn.domain.usecase

import com.dindinn.domain.model.FoodModel
import com.dindinn.domain.repository.SearchRepository
import com.dindinn.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : SingleUseCase<List<FoodModel>>() {

    override fun buildUseCaseSingle(): Single<List<FoodModel>> {
        return searchRepository.getSearchResult()
    }
}