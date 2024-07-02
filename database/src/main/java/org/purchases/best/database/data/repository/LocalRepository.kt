package org.purchases.best.database.data.repository

interface LocalRepository {
    suspend fun saveList(listWithPurchasesInfo: org.purchases.core.model.info.ListWithPurchasesInfo)
    suspend fun getLists(): List<org.purchases.core.model.info.ListInfo>
    suspend fun getListWithPurchases(listId: Long): org.purchases.core.model.info.ListWithPurchasesInfo
    suspend fun deleteList(listId: Long)
    suspend fun getPurchase(purchaseId: Long): org.purchases.core.model.info.PurchaseInfo
    suspend fun savePurchase(listId: Long, purchase: org.purchases.core.model.info.PurchaseInfo)
    suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean)
}