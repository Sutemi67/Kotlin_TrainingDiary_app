package apc.appcradle.trainingdiary.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apc.appcradle.trainingdiary.domain.NotificationServiceInterface
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel(
    private val notification: NotificationServiceInterface
) : ViewModel() {

    private var timerJob: Job? = null
    private var timeLeftInMillis: Long = 0

    private val _timeLeft = MutableLiveData(0L)
    val timeLeft: LiveData<Long> = _timeLeft

    private fun sendNotification(text: String) = notification.sendNotification(text)

    fun startTimer(timeLeft: Long) {
        if (timerJob == null || timerJob?.isCancelled == true) {
            timeLeftInMillis = timeLeft * 1000
            timerJob = viewModelScope.launch {
                while (timeLeftInMillis != 0L) {
                    delay(1000L)
                    timeLeftInMillis -= 1000L
                    _timeLeft.postValue(timeLeftInMillis)
                }
                stopTimer()
                sendNotification("Timer ended")
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

}