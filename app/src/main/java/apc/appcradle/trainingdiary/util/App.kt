package apc.appcradle.trainingdiary.util

import android.app.Application
import apc.appcradle.trainingdiary.util.di.appModule
import apc.appcradle.trainingdiary.util.di.dataModule
import apc.appcradle.trainingdiary.util.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App() : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(domainModule, dataModule, appModule)
        }
    }
}