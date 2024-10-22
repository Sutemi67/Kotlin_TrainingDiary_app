package apc.appcradle.trainingdiary.util.di

import apc.appcradle.trainingdiary.ui.gym.GymViewModel
import apc.appcradle.trainingdiary.ui.home.HomeViewModel
import apc.appcradle.trainingdiary.ui.running.RunningViewModel
import apc.appcradle.trainingdiary.ui.timer.TimerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<GymViewModel> { GymViewModel() }
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<RunningViewModel> { RunningViewModel(get()) }
    viewModel<TimerViewModel> { TimerViewModel(get()) }
}