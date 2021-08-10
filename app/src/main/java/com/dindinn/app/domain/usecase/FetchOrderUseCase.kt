package com.dindinn.app.domain.usecase

import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.repository.OrderRepository
import com.dindinn.app.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) : SingleUseCase<DindinnOrder>() {

    override fun buildUseCaseSingle(): Single<DindinnOrder> {
        return orderRepository.getDindinnOrders()
    }
}