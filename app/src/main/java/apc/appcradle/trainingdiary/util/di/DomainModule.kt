package apc.appcradle.trainingdiary.util.di

import apc.appcradle.trainingdiary.domain.Interactor
import apc.appcradle.trainingdiary.domain.InteractorInterface
import apc.appcradle.trainingdiary.domain.NotificationServiceInterface
import apc.appcradle.trainingdiary.ui.timer.NotificationService
import org.koin.dsl.module

val domainModule = module {
    single<InteractorInterface> { Interactor(get()) }
    single<NotificationServiceInterface> { NotificationService(get()) }
}