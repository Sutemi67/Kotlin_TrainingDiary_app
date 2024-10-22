package apc.appcradle.trainingdiary.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RunningViewModel : ViewModel() {

    private val _greeting = MutableLiveData("Let's begin you training today!")
    val greeting: LiveData<String> = _greeting

    private val _timeElapsed = MutableLiveData<Long>()
    val timeElapsed: LiveData<Long> get() = _timeElapsed

    private var stopwatchJob: Job? = null
    private var timeInSeconds = 0L

    fun startStopwatch() {
        if (stopwatchJob == null || stopwatchJob?.isCancelled == true) {
            stopwatchJob = viewModelScope.launch {
                while (true) {
                    delay(1000L) // Каждую секунду
                    timeInSeconds++
                    _timeElapsed.postValue(timeInSeconds) // Обновляем LiveData
                }
            }
        }
    }

    fun stopStopwatch() {
        stopwatchJob?.cancel()
    }

    fun resetStopwatch() {
        stopStopwatch()
        timeInSeconds = 0L
        _timeElapsed.postValue(timeInSeconds)
    }
}