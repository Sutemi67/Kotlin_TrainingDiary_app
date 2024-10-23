package apc.appcradle.trainingdiary.ui.timer

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import apc.appcradle.trainingdiary.databinding.FragmentTimerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding
    private val vm by viewModel<TimerViewModel>()
    private var inputConvert = 0L
    private var message = ""
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (!isGranted) {
                showPermissionDeniedDialog()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

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

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Разрешение на уведомления отключено")
            .setMessage("Пожалуйста, включите уведомления в настройках приложения, чтобы получать важные уведомления.")
            .setPositiveButton("Позволить") { dialog, _ ->
                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:${requireContext().packageName}")
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Не позволять") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {}
    }
}