package apc.appcradle.trainingdiary.domain

interface RepositoryInterface {
    fun saveRunTime(time: Long)
    fun getRunTime(): Long
}