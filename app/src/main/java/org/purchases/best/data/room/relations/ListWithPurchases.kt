package org.purchases.best.data.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import org.purchases.best.data.room.entities.ListEntity
import org.purchases.best.data.room.entities.PurchaseEntity

data class ListWithPurchases(
    @Embedded
    val list: ListEntity = ListEntity(),

    @Relation(
        parentColumn = "list_id",
        entityColumn = "list_id"
    )
    val purchases: List<PurchaseEntity> = listOf()
)