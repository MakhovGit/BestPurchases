package org.purchases.best.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.purchases.best.intents.MainIntent

val appKoinModule = module {
    viewModel { MainIntent() }
}