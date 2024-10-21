package apc.appcradle.trainingdiary.util.di

import apc.appcradle.trainingdiary.ui.dashboard.GymViewModel
import apc.appcradle.trainingdiary.ui.home.HomeViewModel
import apc.appcradle.trainingdiary.ui.notifications.RunningViewModel
import apc.appcradle.trainingdiary.ui.timer.TimerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<GymViewModel> { GymViewModel() }
    viewModel<HomeViewModel> { HomeViewModel() }
    viewModel<RunningViewModel> { RunningViewModel() }
    viewModel<TimerViewModel> { TimerViewModel() }
}