package org.purchases.best.database.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.purchases.best.database.data.room.dao.ListDao
import org.purchases.best.database.data.room.dao.ListsWithPurchasesDao
import org.purchases.best.database.data.room.dao.PurchaseDao
import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.best.database.data.room.entities.PurchaseEntity

@Database(
    entities = [
        ListEntity::class,
        PurchaseEntity::class
    ], version = 1, exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getListDao(): ListDao
    abstract fun getPurchaseDao(): PurchaseDao
    abstract fun getListWithPurchasesDao(): ListsWithPurchasesDao
}