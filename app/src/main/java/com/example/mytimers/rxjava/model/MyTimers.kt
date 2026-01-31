package com.example.mytimers.rxjava.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MyTimers  {
    // Возвращаем Observable, который тикает каждую секунду
    fun getTimerObservable(): Observable<Long> {
        return Observable.interval(1, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}