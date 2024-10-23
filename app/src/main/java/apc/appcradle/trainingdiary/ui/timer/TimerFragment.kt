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
        val hours = (time / 1000) / 360
        val minutes = (time / 1000) / 60
        val seconds = (time / 1000) % 60
        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        binding.counter.text = timeFormatted
    }

    private fun setOnClickListeners() {
        binding.startButton.setOnClickListener {
            if (!binding.input.text.isNullOrEmpty()) {
                vm.startTimer(binding.input.text.toString().toLong())
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