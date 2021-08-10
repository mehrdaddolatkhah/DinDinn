package com.dindinn.app.domain.usecase

import com.dindinn.app.domain.model.FoodModel
import com.dindinn.app.domain.repository.SearchRepository
import com.dindinn.app.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : SingleUseCase<List<FoodModel>>() {

    override fun buildUseCaseSingle(): Single<List<FoodModel>> {
        return searchRepository.getSearchResult()
    }
}