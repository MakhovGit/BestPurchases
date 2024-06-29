package org.purchases.best.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.data.repository.LocalRepository
import org.purchases.best.data.repository.LocalRepositoryImpl
import org.purchases.best.data.room.LocalDatabase
import org.purchases.best.ui.screens.add_item_screen.AddItemScreenViewModel
import org.purchases.best.ui.screens.create_list_screen.CreateListScreenViewModel
import org.purchases.best.ui.screens.home_screen.HomeScreenReducer
import org.purchases.best.ui.screens.home_screen.HomeScreenStateHolder
import org.purchases.best.ui.screens.home_screen.HomeScreenViewModel
import org.purchases.best.ui.screens.list_screen.ListScreenReducer
import org.purchases.best.ui.screens.list_screen.ListScreenStateHolder
import org.purchases.best.ui.screens.list_screen.ListScreenViewModel
import org.purchases.best.utils.db_mappers.ListMapper
import org.purchases.best.utils.db_mappers.ListWithPurchasesMapper
import org.purchases.best.utils.db_mappers.PurchaseMapper

val appKoinModule = module {
    single { "localdatabase.db" }
    single {
        Room.databaseBuilder(context = get(), klass = LocalDatabase::class.java, name = get())
            .build()
    }
    single { ListMapper() }
    single { PurchaseMapper() }
    single { ListWithPurchasesMapper(purchaseMapper = get()) }
    single<LocalRepository> {
        LocalRepositoryImpl(
            listDao = get<LocalDatabase>().getListDao(),
            purchaseDao = get<LocalDatabase>().getPurchaseDao(),
            listsWithPurchasesDao = get<LocalDatabase>().getListWithPurchasesDao(),
            listMapper = get(),
            listWithPurchasesMapper = get(),
            purchaseMapper = get()
        )
    }
    single<DatabaseInteractor> { DatabaseInteractor(repository = get()) }
    single { HomeScreenReducer() }
    single { HomeScreenStateHolder(reducer = get()) }
    single { ListScreenReducer() }
    single { ListScreenStateHolder(reducer = get()) }
    viewModel { HomeScreenViewModel(databaseInteractor = get(), stateHolder = get()) }
    viewModel { CreateListScreenViewModel(databaseInteractor = get()) }
    viewModel { ListScreenViewModel(databaseInteractor = get(), stateHolder = get()) }
    viewModel { AddItemScreenViewModel(databaseInteractor = get()) }
}