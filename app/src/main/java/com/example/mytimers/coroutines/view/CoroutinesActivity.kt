package com.example.mytimers.coroutines.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mytimers.coroutines.viewmodel.MyTimers2ViewModel
import com.example.mytimers.databinding.ActivityCoroutinesBinding
import java.util.Locale

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesBinding
    private val viewModel : MyTimers2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Наблюдаем за изменением времени
        viewModel.timeInSeconds.observe(this) { seconds ->
            updateTimerText(seconds)
        }

        // Наблюдаем за статусом (для текста на кнопке)
        viewModel.isRunning.observe(this) { isRunning ->
            binding.startStopButton.text = if (isRunning) "Пауза" else "Старт"
        }

        binding.startStopButton.setOnClickListener {
            viewModel.startPauseTimer()
        }

        binding.resetButton.setOnClickListener {
            viewModel.resetTimer()
        }
    }

    private fun updateTimerText(timeInSeconds: Long) {
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        binding.timerTextView.text =
            String.Companion.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }
}