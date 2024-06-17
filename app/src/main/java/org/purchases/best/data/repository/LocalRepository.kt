package org.purchases.best.data.repository

import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo

interface LocalRepository {
    suspend fun saveList(listWithPurchasesInfo: ListWithPurchasesInfo)
    suspend fun getLists(): List<ListInfo>
    suspend fun getListWithPurchases(listId: Long): ListWithPurchasesInfo
    suspend fun deleteList(listId: Long)
    suspend fun getPurchase(purchaseId: Long): PurchaseInfo
    suspend fun savePurchase(listId: Long, purchase: PurchaseInfo)
    suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean)
}