package com.dindinn.domain.usecase

import com.dindinn.domain.model.DindinnOrder
import com.dindinn.domain.repository.OrderRepository
import com.dindinn.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) : SingleUseCase<DindinnOrder>() {

    override fun buildUseCaseSingle(): Single<DindinnOrder> {
        return orderRepository.getDindinnOrders()
    }
}