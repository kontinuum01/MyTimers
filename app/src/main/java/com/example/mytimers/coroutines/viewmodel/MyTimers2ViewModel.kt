package com.example.mytimers.coroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytimers.coroutines.model.MyTimers2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyTimers2ViewModel : ViewModel() {

    private val timerLogic = MyTimers2()
    private val scope = CoroutineScope(Dispatchers.IO)
    private var job : Job? = null

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
        job?.cancel()
        job = scope.launch {
            timerLogic.getTimerFlow()
                .collect {
                    withContext(Dispatchers.Main) {
                        _timeInSeconds.value = (_timeInSeconds.value ?: 0) + 1
                    }

                }
        }

    }

    private fun pauseTimer() {
        _isRunning.value = false
        job?.cancel()
    }

    fun resetTimer() {
        pauseTimer()
        _timeInSeconds.value = 0
    }

    // Очистка при уничтожении ViewModel
    override fun onCleared() {
        super.onCleared()
        job?.cancel()

    }
}