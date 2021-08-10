package com.dindinn.app.data.repository

import com.dindinn.app.data.source.remote.DinDinnService
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.repository.OrderRepository
import io.reactivex.Single

class OrderRepositoryImpl(
    private val dinDinnService: DinDinnService
) : OrderRepository {

    override fun getDindinnOrders(): Single<DindinnOrder> {
        return dinDinnService.getOrders()
    }
}