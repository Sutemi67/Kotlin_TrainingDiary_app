package apc.appcradle.trainingdiary.ui.running

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apc.appcradle.trainingdiary.domain.InteractorInterface
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RunningViewModel(
    private val interactor: InteractorInterface
) : ViewModel() {

    private var stopwatchJob: Job? = null
    private var timeRunToday = 0L
    private var timeRunBefore = getRunTime()

    private val _greeting = MutableLiveData("Let's begin you training today!")
    val greeting: LiveData<String> = _greeting

    private val _timeElapsed = MutableLiveData<Long>()
    val timeElapsed: LiveData<Long> = _timeElapsed

    fun startStopwatch() {
        if (stopwatchJob == null || stopwatchJob?.isCancelled == true) {
            stopwatchJob = viewModelScope.launch {
                while (true) {
                    delay(1000L)
                    timeRunToday++
                    _timeElapsed.postValue(timeRunToday)
                }
            }
        }
    }

    fun stopStopwatch() {
        stopwatchJob?.cancel()
    }

    fun resetStopwatch() {
        stopStopwatch()
        timeRunBefore += timeRunToday
        saveRunTime(timeRunBefore)
        timeRunToday = 0L
        _timeElapsed.postValue(timeRunToday)
    }

    private fun saveRunTime(time: Long) = interactor.saveRunTime(time)
    private fun getRunTime(): Long = interactor.getRunTime()

}