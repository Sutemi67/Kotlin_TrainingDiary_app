package apc.appcradle.trainingdiary.util.di

import apc.appcradle.trainingdiary.domain.Interactor
import apc.appcradle.trainingdiary.domain.InteractorInterface
import org.koin.dsl.module

val domainModule = module {
    single<InteractorInterface> { Interactor(get()) }
}