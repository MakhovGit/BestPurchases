package org.purchases.home_screen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.purchases.home_screen.ui.home_screen.HomeScreenReducer
import org.purchases.home_screen.ui.home_screen.HomeScreenStateHolder
import org.purchases.home_screen.ui.home_screen.HomeScreenViewModel

val homeScreenKoinModule = module {
    singleOf(::HomeScreenReducer)
    singleOf(::HomeScreenStateHolder)
    viewModelOf(::HomeScreenViewModel)
}