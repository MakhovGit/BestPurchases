package org.purchases.best.database.data.room.db_mappers

import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.purchases.core.model.info.PurchaseInfo

class PurchaseMapper {
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
}