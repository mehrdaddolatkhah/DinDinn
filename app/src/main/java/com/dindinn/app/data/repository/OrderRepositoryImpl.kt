package com.dindinn.app.data.repository

import android.content.Context
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.repository.OrderRepository
import com.dindinn.app.util.Utils
import io.reactivex.Single

class OrderRepositoryImpl(
    private val context: Context
) : OrderRepository {

    override fun getDindinnOrders(): Single<DindinnOrder> {
        return Utils.getLocalOrders(context)
    }
}