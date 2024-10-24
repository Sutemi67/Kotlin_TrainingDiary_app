package apc.appcradle.trainingdiary.domain

interface RepositoryInterface {
    fun saveRunTime(time: Long)
    fun getRunTime(): Long
    fun getThemeValue():Int
    fun saveTheme(mode: Int)
}