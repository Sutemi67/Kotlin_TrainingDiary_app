package apc.appcradle.trainingdiary.ui.timer

import androidx.lifecycle.ViewModel
import apc.appcradle.trainingdiary.domain.NotificationServiceInterface

class TimerViewModel(
    private val notification: NotificationServiceInterface
) : ViewModel() {

    fun sendNotification(text: String) = notification.sendNotification(text)
}