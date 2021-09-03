package com.dindinn.presentation.order

import androidx.lifecycle.MutableLiveData
import com.dindinn.domain.model.DindinnOrder
import com.dindinn.domain.usecase.FetchOrderUseCase
import com.dindinn.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val fetchOrderUseCase: FetchOrderUseCase
) :
    BaseViewModel() {

    val dindinnOrderLiveData = MutableLiveData<DindinnOrder>()
    val callAlert = MutableLiveData(0)

    fun getOrders() {
        fetchOrderUseCase.execute(
            onSuccess = {
                dindinnOrderLiveData.value = it
            },
            onError = {
                // todo : handle error
                it.printStackTrace()
            }
        )
    }
}