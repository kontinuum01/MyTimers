package com.example.mytimers.rxjava

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mytimers.databinding.ActivityRxjavaBinding
import java.util.Locale

class RxJavaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRxjavaBinding
    private val viewModel: MyTimersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
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
            String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

}