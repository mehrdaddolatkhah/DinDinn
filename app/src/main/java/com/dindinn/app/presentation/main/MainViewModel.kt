package com.dindinn.app.presentation.main

import android.annotation.SuppressLint
import com.dindinn.app.presentation.base.BaseViewModel
import com.dindinn.app.util.constants.ConstantValues
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel : BaseViewModel() {

    private var globalTimeValue = 0L
    lateinit var globalTimeObservable: Observable<Long>

    @SuppressLint("CheckResult")
    fun globalTimerObservable(): Observable<Long> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMap {
                globalTimeObservable = Observable.create<Long> { emitter ->

                    if (globalTimeValue == 0L) {
                        globalTimeValue = ConstantValues.FIXED_START_TIME
                    }

                    globalTimeValue += 1000

                    emitter.onNext(globalTimeValue)
                    emitter.onComplete()
                }
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())

                return@flatMap globalTimeObservable
            }
    }


    @SuppressLint("CheckResult")
    override fun onCleared() {
        super.onCleared()
        if (::globalTimeObservable.isInitialized) {
            globalTimeObservable.unsubscribeOn(Schedulers.io())
        }
    }
}