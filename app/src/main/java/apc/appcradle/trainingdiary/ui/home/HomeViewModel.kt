package apc.appcradle.trainingdiary.ui.home

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apc.appcradle.trainingdiary.R
import apc.appcradle.trainingdiary.domain.InteractorInterface

class HomeViewModel(
    private val interactor: InteractorInterface
) : ViewModel() {

    private val _greeting =
        MutableLiveData("Have a good day!\n\nKeep going in your progress!\nConsistency - is a key.")
    val greeting: LiveData<String> = _greeting

    @SuppressLint("DefaultLocale")
    fun getRunBefore(): String {
        val gotTime = interactor.getRunTime()
        val hours = gotTime / 3600
        val minutes = (gotTime % 3600) / 60
        val seconds = gotTime % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    fun loadTheme(switchThemeIcon: ImageView) {
        when (interactor.getThemeValue()) {
            2 -> {
                setDefaultNightMode(MODE_NIGHT_YES)
                switchThemeIcon.setImageResource(R.drawable.ic_night_theme)
            }

            else -> {
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchThemeIcon.setImageResource(R.drawable.ic_light_theme)
            }
        }
    }

    fun changeTheme(switchThemeIcon: ImageView) {
        if (interactor.getThemeValue()==1){
            interactor.saveTheme(2)
            switchThemeIcon.setImageResource(R.drawable.ic_light_theme)
            setDefaultNightMode(MODE_NIGHT_YES)


        }else{
            interactor.saveTheme(1)
            switchThemeIcon.setImageResource(R.drawable.ic_night_theme)
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }
}