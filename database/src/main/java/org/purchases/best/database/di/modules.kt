package org.purchases.best.database.di

import androidx.room.Room
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.purchases.best.database.data.interactors.DatabaseInteractor
import org.purchases.best.database.data.repository.LocalRepository
import org.purchases.best.database.data.repository.LocalRepositoryImpl
import org.purchases.best.database.data.room.LocalDatabase
import org.purchases.best.database.data.room.db_mappers.ListMapper
import org.purchases.best.database.data.room.db_mappers.ListWithPurchasesMapper
import org.purchases.best.database.data.room.db_mappers.PurchaseMapper

val databaseKoinModule = module {
    single { "localdatabase.db" }
    single {
        Room.databaseBuilder(context = get(), klass = LocalDatabase::class.java, name = get())
            .build()
    }
    singleOf(::ListMapper)
    singleOf(::PurchaseMapper)
    singleOf(::ListWithPurchasesMapper)
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
    singleOf(::DatabaseInteractor)

}