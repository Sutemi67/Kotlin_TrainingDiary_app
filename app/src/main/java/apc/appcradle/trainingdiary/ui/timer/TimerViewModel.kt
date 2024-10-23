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
    private var timeLeftInSeconds: Long = 0L

    private val _timeLeft = MutableLiveData(0L)
    val timeLeft: LiveData<Long> = _timeLeft

    private fun sendNotification(text: String) = notification.sendNotification(text)

    fun startTimer(timeLeft: Long, switcher: Boolean, message: String) {
        if (timerJob == null || timerJob?.isCancelled == true) {
            timeLeftInSeconds = timeLeft
            timerJob = viewModelScope.launch {
                while (timeLeftInSeconds != 0L) {
                    delay(1000L)
                    timeLeftInSeconds--
                    _timeLeft.postValue(timeLeftInSeconds)
                }
                stopTimer()
                sendNotification(message)
                if (switcher) {
                    startTimer(timeLeft, true, message)
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

}