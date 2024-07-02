package org.purchases.best.database.data.room.db_mappers

import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.best.database.data.room.relations.ListWithPurchases
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo

class ListWithPurchasesMapper(
    private val purchaseMapper: PurchaseMapper
) {
    fun map(listWithPurchases: ListWithPurchases): ListWithPurchasesInfo {
        val purchasesNotChecked: MutableList<PurchaseInfo> =
            mutableListOf()
        val purchasesChecked: MutableList<PurchaseInfo> =
            mutableListOf()
        listWithPurchases.purchases.forEach { purchase ->
            if (purchase.checked) {
                purchasesChecked.add(purchaseMapper.map(purchase))
            } else {
                purchasesNotChecked.add(purchaseMapper.map(purchase))
            }
        }
        return ListWithPurchasesInfo(
            id = listWithPurchases.list.listId,
            name = listWithPurchases.list.listName,
            purchasesNotChecked = purchasesNotChecked.toList(),
            purchasesChecked = purchasesChecked.toList()
        )
    }

    fun map(listWithPurchasesInfo: ListWithPurchasesInfo) =
        ListEntity(
            listId = listWithPurchasesInfo.id,
            listName = listWithPurchasesInfo.name
        )
}