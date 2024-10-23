package apc.appcradle.trainingdiary.ui.running

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import apc.appcradle.trainingdiary.R
import apc.appcradle.trainingdiary.databinding.FragmentRunningBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding
    private val vm by viewModel<RunningViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(layoutInflater, container, false)
        val textView: TextView = binding.greetings
        vm.greeting.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        vm.timeElapsed.observe(viewLifecycleOwner) { timeInSeconds ->
            val hours = timeInSeconds / 3600
            val minutes = timeInSeconds / 60
            val seconds = timeInSeconds % 60
            binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }

    private fun setOnClickListeners() {
        binding.start.setOnClickListener {
            vm.startStopwatch()
            binding.timerText.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.scale_up_filled
                )
            )
        }
        binding.pause.setOnClickListener { vm.stopStopwatch() }
        binding.endAndReset.setOnClickListener {
            vm.resetStopwatch()
            val navOptions = NavOptions.Builder()
                .setEnterAnim(android.R.anim.slide_in_left) // Анимация входа
                .setExitAnim(android.R.anim.slide_out_right) // Анимация выхода
                .setPopEnterAnim(android.R.anim.fade_in) // Анимация при возврате назад
                .setPopExitAnim(android.R.anim.fade_out) // Анимация при закрытии
                .build()
            findNavController().navigate(R.id.navigation_home, null, navOptions)
        }
    }
}