package com.dindinn.app.presentation.order

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.usecase.FetchOrderUseCase
import com.dindinn.app.presentation.base.BaseViewModel

class OrderViewModel @ViewModelInject constructor(
    private val fetchOrderUseCase: FetchOrderUseCase
) :
    BaseViewModel() {

    val dindinnOrderLiveData = MutableLiveData<DindinnOrder>()

    fun getOrders() {

        fetchOrderUseCase.execute(
            onSuccess = {
                dindinnOrderLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}