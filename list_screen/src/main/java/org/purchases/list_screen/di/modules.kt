package org.purchases.list_screen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.purchases.list_screen.ui.list_screen.ListScreenReducer
import org.purchases.list_screen.ui.list_screen.ListScreenStateHolder
import org.purchases.list_screen.ui.list_screen.ListScreenViewModel

val listScreenKoinModule = module {
    singleOf(::ListScreenReducer)
    singleOf(::ListScreenStateHolder)
    viewModelOf(::ListScreenViewModel)
}