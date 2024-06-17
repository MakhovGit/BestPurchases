package org.purchases.best.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.data.repository.LocalRepository
import org.purchases.best.data.repository.LocalRepositoryImpl
import org.purchases.best.data.room.LocalDatabase
import org.purchases.best.ui.screens.add_item.AddItemScreenViewModel
import org.purchases.best.ui.screens.create_list.CreateListScreenViewModel
import org.purchases.best.ui.screens.home.HomeScreenViewModel
import org.purchases.best.ui.screens.list.ListScreenViewModel

val appKoinModule = module {
    single { "localdatabase.db" }
    single {
        Room.databaseBuilder(context = get(), klass = LocalDatabase::class.java, name = get())
            .build()
    }
    single<LocalRepository> {
        LocalRepositoryImpl(
            listDao = get<LocalDatabase>().getListDao(),
            purchaseDao = get<LocalDatabase>().getPurchaseDao(),
            listsWithPurchasesDao = get<LocalDatabase>().getListWithPurchasesDao(),
        )
    }
    single<DatabaseInteractor> { DatabaseInteractor(repository = get()) }
    viewModel { HomeScreenViewModel(databaseInteractor = get()) }
    viewModel { CreateListScreenViewModel(databaseInteractor = get()) }
    viewModel { ListScreenViewModel(databaseInteractor = get()) }
    viewModel { AddItemScreenViewModel(databaseInteractor = get()) }
}