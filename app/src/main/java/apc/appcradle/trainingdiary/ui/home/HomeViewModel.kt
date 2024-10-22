package apc.appcradle.trainingdiary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apc.appcradle.trainingdiary.domain.InteractorInterface

class HomeViewModel(
    private val interactor: InteractorInterface
) : ViewModel() {

    private val _greeting =
        MutableLiveData("Good day!\nToday you trained about 2hrs.\nCongrats.\nIf you want, you can choose another training in menu below.")
    val greeting: LiveData<String> = _greeting

    fun getRunTime(): Long = interactor.getRunTime()
}