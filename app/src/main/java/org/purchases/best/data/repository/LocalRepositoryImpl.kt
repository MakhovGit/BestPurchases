package org.purchases.best.data.repository

import org.purchases.best.data.room.dao.ListDao
import org.purchases.best.data.room.dao.ListsWithPurchasesDao
import org.purchases.best.data.room.dao.PurchaseDao
import org.purchases.best.data.room.entities.PurchaseEntity
import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.utils.DbMapper

class LocalRepositoryImpl(
    private val listDao: ListDao,
    private val listsWithPurchasesDao: ListsWithPurchasesDao,
    private val purchaseDao: PurchaseDao,
    private val dbMapper: DbMapper
) : LocalRepository {
    override suspend fun saveList(listWithPurchasesInfo: ListWithPurchasesInfo) {
        val listId = listDao.saveList(dbMapper.map(listWithPurchasesInfo))
        val purchases = listWithPurchasesInfo.purchasesNotChecked.map { dbMapper.map(it, listId) }
        purchaseDao.insertPurchases(purchases)
    }

    override suspend fun getLists(): List<ListInfo> {
        val lists = listDao.getLists() ?: listOf()
        return lists.map { dbMapper.map(it) }
    }

    override suspend fun getListWithPurchases(listId: Long) =
        dbMapper.map(listsWithPurchasesDao.getListWithPurchases(listId))

    override suspend fun deleteList(listId: Long) {
        purchaseDao.deletePurchase(listId)
        listDao.deleteList(listId)
    }

    override suspend fun getPurchase(purchaseId: Long) =
        dbMapper.map(purchaseDao.getPurchase(purchaseId) ?: PurchaseEntity())

    override suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean) {
        purchaseDao.setPurchaseStatus(purchaseId, isChecked)
    }

    override suspend fun savePurchase(listId: Long, purchase: PurchaseInfo) {
        purchaseDao.insertPurchase(dbMapper.map(purchase, listId))
    }

}