package apc.appcradle.trainingdiary.util.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import apc.appcradle.trainingdiary.data.Repository
import apc.appcradle.trainingdiary.domain.RepositoryInterface
import apc.appcradle.trainingdiary.util.MAIN_SHARED_PREFS_KEY
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<RepositoryInterface> { Repository(get()) }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(MAIN_SHARED_PREFS_KEY, MODE_PRIVATE)
    }
}