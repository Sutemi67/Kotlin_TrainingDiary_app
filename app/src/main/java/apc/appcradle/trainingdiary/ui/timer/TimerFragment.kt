package apc.appcradle.trainingdiary.ui.timer

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import apc.appcradle.trainingdiary.R
import apc.appcradle.trainingdiary.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {
    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private var isRunning = false
    private var timeLeftInMillis: Long = 10000 // 1 минута
    private var userInterval: Long = 10 // Интервал для отправки уведомлений (в секундах)
    private lateinit var binding: FragmentTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timerTextView = binding.counter
        binding.startButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
            } else {
                countDownTimer.cancel()
                startTimer()
            }
        }
    }

    private fun startTimer() {
        if (binding.input.text.isNullOrEmpty()) {
            return
        }
        timeLeftInMillis = binding.input.text.toString().toLong() * 1000
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                sendNotification("Таймер завершен!")
                isRunning = false
            }
        }
        countDownTimer.start()
        isRunning = true
    }

    @SuppressLint("DefaultLocale")
    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timerTextView.text = timeFormatted
    }

    private fun sendNotification(message: String) {
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "timer_channel",
                "Timer Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(requireContext(), "timer_channel")
            .setContentTitle("Таймер")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_timer)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {}
    }
}