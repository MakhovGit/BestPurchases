package org.purchases.best.database.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.purchases.best.database.data.room.relations.ListWithPurchases

@Dao
interface ListsWithPurchasesDao {
    @Transaction
    @Query("SELECT * FROM lists WHERE list_id = :listId")
    suspend fun getListWithPurchases(listId: Long): ListWithPurchases
}