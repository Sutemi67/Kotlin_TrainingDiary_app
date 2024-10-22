package apc.appcradle.trainingdiary.ui.gym

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import apc.appcradle.trainingdiary.databinding.FragmentGymBinding

class GymFragment : Fragment() {

    private var _binding: FragmentGymBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gymViewModel =
            ViewModelProvider(this)[GymViewModel::class.java]

        _binding = FragmentGymBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGym
        gymViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}