package apc.appcradle.trainingdiary.ui.gym

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GymViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Under development."
    }
    val text: LiveData<String> = _text
}