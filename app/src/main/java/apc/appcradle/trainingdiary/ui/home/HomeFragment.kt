package apc.appcradle.trainingdiary.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apc.appcradle.trainingdiary.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val vm by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        vm.greeting.observe(viewLifecycleOwner) { binding.textHome.text = it }
        binding.runBeforeCount.text = getRunBefore()
        return binding.root
    }

    @SuppressLint("DefaultLocale")
    private fun getRunBefore(): String {
        val gotTime = vm.getRunTime()
        val hours = gotTime / 360
        val minutes = gotTime / 60
        val seconds = gotTime % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}