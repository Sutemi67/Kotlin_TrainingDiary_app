package apc.appcradle.trainingdiary.data

import android.content.SharedPreferences
import apc.appcradle.trainingdiary.domain.RepositoryInterface
import apc.appcradle.trainingdiary.util.RUNNING_TIME_KEY
import apc.appcradle.trainingdiary.util.THEME_VALUE_KEY

class Repository(
    private val preferences: SharedPreferences
) : RepositoryInterface {

    override fun saveRunTime(time: Long) =
        preferences.edit().putLong(RUNNING_TIME_KEY, time).apply()

    override fun getRunTime(): Long = preferences.getLong(RUNNING_TIME_KEY, 0L)
    override fun getThemeValue(): Int = preferences.getInt(THEME_VALUE_KEY, 1)
    override fun saveTheme(mode: Int) = preferences.edit().putInt(THEME_VALUE_KEY, mode).apply()
}