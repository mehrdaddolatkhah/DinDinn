package com.dindinn.domain.repository

import com.dindinn.domain.model.DindinnOrder
import io.reactivex.Single

interface OrderRepository {

    fun getDindinnOrders(): Single<DindinnOrder>
}