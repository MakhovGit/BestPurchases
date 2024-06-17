package org.purchases.best.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.purchases.best.utils.EMPTY
import org.purchases.best.utils.ZERO

@Entity(tableName = "purchases")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "purchase_id")
    val purchaseId: Long = Long.ZERO,

    @field:ColumnInfo(name = "list_id")
    val extListId: Long = Long.ZERO,

    @field:ColumnInfo(name = "purchase_info")
    val purchaseInfo: String = String.EMPTY,

    @field:ColumnInfo(name = "checked")
    val checked: Boolean = false
)