package apc.appcradle.trainingdiary.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RunningViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Let's begin you training today!"
    }
    val text: LiveData<String> = _text
}