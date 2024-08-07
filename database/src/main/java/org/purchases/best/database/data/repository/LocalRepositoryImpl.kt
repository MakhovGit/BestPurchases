package org.purchases.best.database.data.repository

import org.purchases.best.database.data.room.dao.ListDao
import org.purchases.best.database.data.room.dao.ListsWithPurchasesDao
import org.purchases.best.database.data.room.dao.PurchaseDao
import org.purchases.best.database.data.room.db_mappers.ListMapper
import org.purchases.best.database.data.room.db_mappers.ListWithPurchasesMapper
import org.purchases.best.database.data.room.db_mappers.PurchaseMapper
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.purchases.core.model.info.ListInfo
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo

class LocalRepositoryImpl(
    private val listDao: ListDao,
    private val listsWithPurchasesDao: ListsWithPurchasesDao,
    private val purchaseDao: PurchaseDao,
    private val listMapper: ListMapper,
    private val listWithPurchasesMapper: ListWithPurchasesMapper,
    private val purchaseMapper: PurchaseMapper
) : LocalRepository {
    override suspend fun saveList(listWithPurchasesInfo: ListWithPurchasesInfo) {
        val listId = listDao.saveList(listWithPurchasesMapper.map(listWithPurchasesInfo))
        val purchases =
            listWithPurchasesInfo.purchasesNotChecked.map { purchaseMapper.map(it, listId) }
        purchaseDao.insertPurchases(purchases)
    }

    override suspend fun getLists(): List<ListInfo> {
        val lists = listDao.getLists() ?: listOf()
        return lists.map { listMapper.map(it) }
    }

    override suspend fun getListWithPurchases(listId: Long) =
        listWithPurchasesMapper.map(listsWithPurchasesDao.getListWithPurchases(listId))

    override suspend fun deleteList(listId: Long) {
        purchaseDao.deletePurchase(listId)
        listDao.deleteList(listId)
    }

    override suspend fun getPurchase(purchaseId: Long) =
        purchaseMapper.map(purchaseDao.getPurchase(purchaseId) ?: PurchaseEntity())

    override suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean) {
        purchaseDao.setPurchaseStatus(purchaseId, isChecked)
    }

    override suspend fun savePurchase(listId: Long, purchase: PurchaseInfo) {
        purchaseDao.insertPurchase(purchaseMapper.map(purchase, listId))
    }

}