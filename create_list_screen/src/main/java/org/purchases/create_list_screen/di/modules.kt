package org.purchases.create_list_screen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.purchases.create_list_screen.ui.create_list_screen.CreateListScreenViewModel

val createListScreenKoinModule = module {
    viewModelOf(::CreateListScreenViewModel)
}