package apc.appcradle.trainingdiary.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apc.appcradle.trainingdiary.domain.InteractorInterface

class HomeViewModel(
    private val interactor: InteractorInterface
) : ViewModel() {

    private val _greeting =
        MutableLiveData("Have a good day!\n\nKeep going in your progress!\nConsistency - is a key.")
    val greeting: LiveData<String> = _greeting

    @SuppressLint("DefaultLocale")
    fun getRunBefore(): String {
        val gotTime = interactor.getRunTime()
        val hours = gotTime / 3600
        val minutes = (gotTime % 3600) / 60
        val seconds = gotTime % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}