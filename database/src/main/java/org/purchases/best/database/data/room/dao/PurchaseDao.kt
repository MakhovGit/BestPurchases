package org.purchases.best.database.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.purchases.best.database.data.room.entities.PurchaseEntity

@Dao
interface PurchaseDao {

    @Insert
    suspend fun insertPurchase(purchaseEntity: PurchaseEntity)

    @Insert
    suspend fun insertPurchases(purchases: List<PurchaseEntity>)

    @Query("DELETE FROM purchases WHERE list_id = :listId")
    suspend fun deletePurchase(listId: Long)

    @Query("SELECT * FROM purchases WHERE purchase_id = :purchaseId")
    suspend fun getPurchase(purchaseId: Long): PurchaseEntity?

    @Query("UPDATE purchases SET checked = :isChecked WHERE purchase_id = :purchaseId")
    suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean)
}