package org.purchases.best.di

import org.koin.dsl.module
import org.purchases.add_item_screen.di.addItemScreenKoinModule
import org.purchases.best.database.di.databaseKoinModule
import org.purchases.create_list_screen.di.createListScreenKoinModule
import org.purchases.home_screen.di.homeScreenKoinModule
import org.purchases.list_screen.di.listScreenKoinModule

val appKoinModule = module {
    includes(
        databaseKoinModule,
        addItemScreenKoinModule,
        createListScreenKoinModule,
        homeScreenKoinModule,
        listScreenKoinModule
    )
}