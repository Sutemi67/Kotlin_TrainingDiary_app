package apc.appcradle.trainingdiary.ui.home

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
        binding.runBeforeCount.text = vm.getRunBefore()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadTheme(binding.switchThemeIcon)
        binding.switchThemeIcon.setOnClickListener {
            vm.changeTheme(binding.switchThemeIcon)
        }
    }
}
