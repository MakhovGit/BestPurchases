package org.purchases.add_item_screen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.purchases.add_item_screen.ui.add_item_screen.AddItemScreenViewModel

val addItemScreenKoinModule = module {
    viewModelOf(::AddItemScreenViewModel)
}