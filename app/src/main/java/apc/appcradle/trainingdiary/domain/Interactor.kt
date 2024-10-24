package apc.appcradle.trainingdiary.domain


class Interactor(
    private val repository: RepositoryInterface
) : InteractorInterface {

    override fun saveRunTime(time: Long) {
        repository.saveRunTime(time)
    }

    override fun getRunTime(): Long =repository.getRunTime()
    override fun getThemeValue(): Int =repository.getThemeValue()
    override fun saveTheme(mode: Int) {
        repository.saveTheme(mode)
    }
}