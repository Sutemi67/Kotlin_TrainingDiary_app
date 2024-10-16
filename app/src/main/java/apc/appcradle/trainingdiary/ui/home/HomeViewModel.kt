package apc.appcradle.trainingdiary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value =
            "Good day!\nToday you trained about 2hrs.\nCongrats.\nIf you want, you can choose another training in menu below."
    }
    val text: LiveData<String> = _text
}