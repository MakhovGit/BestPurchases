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
    private val purchaseDao: PurchaseDao
) : LocalRepository {
    override suspend fun saveList(listWithPurchasesInfo: ListWithPurchasesInfo) {
        val listId = listDao.saveList(DbMapper.map(listWithPurchasesInfo))
        val purchases = listWithPurchasesInfo.purchasesNotChecked.map { DbMapper.map(it, listId) }
        purchaseDao.insertPurchases(purchases)
    }

    override suspend fun getLists(): List<ListInfo> {
        val lists = listDao.getLists() ?: listOf()
        return lists.map { DbMapper.map(it) }
    }

    override suspend fun getListWithPurchases(listId: Long): ListWithPurchasesInfo {
        return DbMapper.map(listsWithPurchasesDao.getListWithPurchases(listId))
    }

    override suspend fun deleteList(listId: Long) {
        purchaseDao.deletePurchase(listId)
        listDao.deleteList(listId)
    }

    override suspend fun getPurchase(purchaseId: Long) =
        DbMapper.map(purchaseDao.getPurchase(purchaseId) ?: PurchaseEntity())

    override suspend fun setPurchaseStatus(purchaseId: Long, isChecked: Boolean) {
        purchaseDao.setPurchaseStatus(purchaseId, isChecked)
    }

    override suspend fun savePurchase(listId: Long, purchase: PurchaseInfo) {
        purchaseDao.insertPurchase(DbMapper.map(purchase, listId))
    }

}