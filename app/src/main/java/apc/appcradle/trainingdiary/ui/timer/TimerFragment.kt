package apc.appcradle.trainingdiary.ui.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apc.appcradle.trainingdiary.databinding.FragmentTimerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding
    private val vm by viewModel<TimerViewModel>()
    private var inputConvert = 0L
    private var message = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        vm.timeLeft.observe(viewLifecycleOwner) { time ->
            updateTimerText(time)
        }
    }

    @SuppressLint("DefaultLocale")
    private fun updateTimerText(time: Long) {
        val hours = time / 3600
        val minutes = (time % 3600) / 60
        val seconds = time % 60
        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        binding.counter.text = timeFormatted
    }

    private fun setOnClickListeners() {
        binding.startButton.setOnClickListener {
            message = if (binding.inputMessage.text.isNullOrEmpty()) {
                "Timer is ended"
            } else {
                binding.inputMessage.text.toString()
            }
            inputConvert = 0L
            if (!binding.inputHours.text.isNullOrEmpty()) {
                inputConvert += binding.inputHours.text.toString().toLong() * 3600
            }
            if (!binding.inputMinutes.text.isNullOrEmpty()) {
                inputConvert += binding.inputMinutes.text.toString().toLong() * 60
            }
            if (!binding.inputSeconds.text.isNullOrEmpty()) {
                inputConvert += binding.inputSeconds.text.toString().toLong()
            }
            if (binding.switcherUnlimitedCounter.isChecked) {
                vm.startTimer(inputConvert, true, message)
            } else {
                vm.startTimer(inputConvert, false, message)
            }
        }
        binding.stopButton.setOnClickListener {
            vm.stopTimer()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {}
    }
}