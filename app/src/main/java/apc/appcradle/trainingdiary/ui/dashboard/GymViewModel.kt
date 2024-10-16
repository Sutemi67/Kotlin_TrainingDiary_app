package apc.appcradle.trainingdiary.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GymViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is GYM Fragment"
    }
    val text: LiveData<String> = _text
}