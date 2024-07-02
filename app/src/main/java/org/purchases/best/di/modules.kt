package org.purchases.best.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.purchases.best.ui.screens.add_item_screen.AddItemScreenViewModel
import org.purchases.best.ui.screens.create_list_screen.CreateListScreenViewModel
import org.purchases.best.ui.screens.home_screen.HomeScreenReducer
import org.purchases.best.ui.screens.home_screen.HomeScreenStateHolder
import org.purchases.best.ui.screens.home_screen.HomeScreenViewModel
import org.purchases.best.ui.screens.list_screen.ListScreenReducer
import org.purchases.best.ui.screens.list_screen.ListScreenStateHolder
import org.purchases.best.ui.screens.list_screen.ListScreenViewModel

val appKoinModule = module {
    single { HomeScreenReducer() }
    single { HomeScreenStateHolder(reducer = get()) }
    single { ListScreenReducer() }
    single { ListScreenStateHolder(reducer = get()) }
    viewModel { HomeScreenViewModel(databaseInteractor = get(), stateHolder = get()) }
    viewModel { CreateListScreenViewModel(databaseInteractor = get()) }
    viewModel { ListScreenViewModel(databaseInteractor = get(), stateHolder = get()) }
    viewModel { AddItemScreenViewModel(databaseInteractor = get()) }
}