package apc.appcradle.trainingdiary.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import apc.appcradle.trainingdiary.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private var _binding: FragmentRunningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val runningViewModel =
            ViewModelProvider(this)[RunningViewModel::class.java]

        _binding = FragmentRunningBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRunning
        runningViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}