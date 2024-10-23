package apc.appcradle.trainingdiary.ui.running

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
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

        binding.start.setOnClickListener { vm.startStopwatch() }
        binding.pause.setOnClickListener { vm.stopStopwatch() }
        binding.endAndReset.setOnClickListener {
            vm.resetStopwatch()
            findNavController().navigate(R.id.navigation_home)
        }

        vm.timeElapsed.observe(viewLifecycleOwner) { timeInSeconds ->
            val hours = timeInSeconds / 360
            val minutes = timeInSeconds / 60
            val seconds = timeInSeconds % 60
            binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}