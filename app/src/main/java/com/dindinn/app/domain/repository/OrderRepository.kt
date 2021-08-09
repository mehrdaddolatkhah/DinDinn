package com.dindinn.app.domain.repository

import com.dindinn.app.domain.model.DindinnOrder
import io.reactivex.Single

interface OrderRepository {

    fun getDindinnOrders(): Single<DindinnOrder>
}