package apc.appcradle.trainingdiary.util.di

import apc.appcradle.trainingdiary.data.Repository
import apc.appcradle.trainingdiary.domain.RepositoryInterface
import org.koin.dsl.module

val dataModule = module {
    single<RepositoryInterface> { Repository() }
}