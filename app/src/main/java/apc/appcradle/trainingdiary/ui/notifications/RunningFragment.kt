package apc.appcradle.trainingdiary.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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
        vm.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.start
    }
}