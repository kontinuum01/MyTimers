package com.example.mytimers.rxjava.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytimers.rxjava.model.MyTimers
import io.reactivex.rxjava3.disposables.Disposable

class MyTimersViewModel : ViewModel() {

    private val timerLogic = MyTimers()
    private var disposable: Disposable? = null

    // LiveData для времени (в секундах)
    private val _timeInSeconds = MutableLiveData<Long>(0)
    val timeInSeconds: LiveData<Long> get() = _timeInSeconds

    // LiveData для статуса (запущен или нет)
    private val _isRunning = MutableLiveData(false)
    val isRunning: LiveData<Boolean> get() = _isRunning

    fun startPauseTimer() {
        if (_isRunning.value == true) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        _isRunning.value = true
        disposable = timerLogic.getTimerObservable()
            .subscribe({
                _timeInSeconds.value = (_timeInSeconds.value ?: 0) + 1
            }, {
                it.printStackTrace()
            })
    }

    private fun pauseTimer() {
        _isRunning.value = false
        disposable?.dispose()
    }

    fun resetTimer() {
        pauseTimer()
        _timeInSeconds.value = 0
    }

    // Очистка при уничтожении ViewModel
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}