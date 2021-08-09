package com.dindinn.app.presentation.order

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.model.OrderDataDetails
import com.dindinn.app.domain.usecase.FetchOrderUseCase
import com.dindinn.app.presentation.base.BaseViewModel
import com.dindinn.app.util.Utils.toMilliSeconds
import com.dindinn.app.util.constants.ConstantValues
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class OrderViewModel @ViewModelInject constructor(
    private val fetchOrderUseCase: FetchOrderUseCase
) :
    BaseViewModel() {

    val dindinnOrderLiveData = MutableLiveData<DindinnOrder>()
    val callAlert = MutableLiveData(0)
    val expireOrder = MutableLiveData(0)
    var globalTime = 0L

    val shouldNavigateToIngredientScreen = MutableLiveData(false)

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

    fun startGlobalTimer(order: DindinnOrder) {
        val globalTimerDisposable =
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap Observable.create<Long> { emitter ->

                        if (globalTime == 0L) {
                            globalTime = ConstantValues.FIXED_START_TIME
                        }

                        emitter.onNext(globalTime)
                        emitter.onComplete()
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    order.data?.forEach { orderDetails ->
                        if (orderDetails.createdAt?.toMilliSeconds() == globalTime) {
                            startOrderCountDown(
                                orderDetails
                            )
                        }
                    }

                    globalTime += 1000
                }

        compositeDisposable.add(globalTimerDisposable)
    }

    fun startOrderCountDown(orderDataDetails: OrderDataDetails) {
        val orderTimerDisposable =
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap Observable.create<Long> { emitter ->

                        val createdAtInMilli = orderDataDetails.createdAt?.toMilliSeconds() ?: 0L
                        val alertAtInMilli = orderDataDetails.alertedAt?.toMilliSeconds() ?: 0L
                        val expiredAtInMilli = orderDataDetails.expiredAt?.toMilliSeconds() ?: 0L

                        val countDown = expiredAtInMilli - globalTime

                        orderDataDetails.orderCountDown?.value =
                            String.format(
                                ConstantValues.COUNTDOWN_TIME_FORMAT,
                                TimeUnit.MILLISECONDS.toMinutes(countDown),
                                TimeUnit.MILLISECONDS.toSeconds(countDown) -
                                        TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(
                                                countDown
                                            )
                                        )
                            )


                        if (globalTime == alertAtInMilli) {
                            callAlert.postValue(orderDataDetails.id)
                        }

                        if (globalTime == expiredAtInMilli) {
                            expireOrder.postValue(orderDataDetails.id)
                        }

                        emitter.onNext(globalTime)
                        emitter.onComplete()
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                }

        compositeDisposable.add(orderTimerDisposable)
    }

    fun navigateToIngredientScreen() {
        shouldNavigateToIngredientScreen.value = true
    }
}