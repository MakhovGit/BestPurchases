package org.purchases.best.utils

import org.purchases.best.data.room.entities.ListEntity
import org.purchases.best.data.room.entities.PurchaseEntity
import org.purchases.best.data.room.relations.ListWithPurchases
import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo

object DbMapper {
    fun map(listWithPurchasesInfo: ListWithPurchasesInfo) =
        ListEntity(
            listId = listWithPurchasesInfo.id,
            listName = listWithPurchasesInfo.name
        )

    fun map(listEntity: ListEntity) =
        ListInfo(
            id = listEntity.listId,
            title = listEntity.listName,
        )

    fun map(purchaseInfo: PurchaseInfo, listId: Long) =
        PurchaseEntity(
            extListId = listId,
            purchaseInfo = purchaseInfo.description,
            checked = purchaseInfo.checked
        )

    fun map(purchaseEntity: PurchaseEntity) =
        PurchaseInfo(
            id = purchaseEntity.purchaseId,
            listId = purchaseEntity.extListId,
            description = purchaseEntity.purchaseInfo,
            checked = purchaseEntity.checked
        )

    fun map(listWithPurchases: ListWithPurchases): ListWithPurchasesInfo {
        val purchasesNotChecked: MutableList<PurchaseInfo> = mutableListOf()
        val purchasesChecked: MutableList<PurchaseInfo> = mutableListOf()
        listWithPurchases.purchases.forEach { purchase ->
            if (purchase.checked) {
                purchasesChecked.add(map(purchase))
            } else {
                purchasesNotChecked.add(map(purchase))
            }
        }
        return ListWithPurchasesInfo(
            id = listWithPurchases.list.listId,
            name = listWithPurchases.list.listName,
            purchasesNotChecked = purchasesNotChecked.toList(),
            purchasesChecked = purchasesChecked.toList()
        )
    }
}