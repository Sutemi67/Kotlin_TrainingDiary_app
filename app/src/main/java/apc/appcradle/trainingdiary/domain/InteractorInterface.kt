package apc.appcradle.trainingdiary.domain

interface InteractorInterface {
    fun saveRunTime(time: Long)
    fun getRunTime():Long
}