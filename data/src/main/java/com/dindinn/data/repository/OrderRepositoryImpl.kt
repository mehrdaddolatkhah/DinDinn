package com.dindinn.data.repository

import com.dindinn.data.source.remote.DinDinnService
import com.dindinn.domain.model.DindinnOrder
import com.dindinn.domain.repository.OrderRepository
import io.reactivex.Single

class OrderRepositoryImpl(
    private val dinDinnService: DinDinnService
) : OrderRepository {

    override fun getDindinnOrders(): Single<DindinnOrder> {
        return dinDinnService.getOrders()
    }
}