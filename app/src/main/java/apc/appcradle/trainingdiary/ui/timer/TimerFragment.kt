package apc.appcradle.trainingdiary.ui.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import apc.appcradle.trainingdiary.databinding.FragmentTimerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {
    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private var isRunning = false
    private var timeLeftInMillis: Long = 10000 // 1 минута
    private lateinit var binding: FragmentTimerBinding
    private val vm by viewModel<TimerViewModel>()

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
                vm.sendNotification("Timer has ended")
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {}
    }
}