package com.example.mytimers.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit


class MyTimers2 {
    // Возвращаем Flow, который тикает каждую секунду
    fun getTimerFlow(period: Long = 1, unit: TimeUnit = TimeUnit.SECONDS): Flow<Long> = flow {
        var count = 0L
        val delayMillis = unit.toMillis(period)

        while (true) {
            emit(count++)
            delay(delayMillis)
        }
    }
        .flowOn(Dispatchers.Default)

}