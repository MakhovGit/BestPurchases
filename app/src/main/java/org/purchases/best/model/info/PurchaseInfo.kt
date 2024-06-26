package org.purchases.best.model.info

import org.purchases.best.utils.EMPTY
import org.purchases.best.utils.ZERO

data class PurchaseInfo(
    val id: Long = Long.ZERO,
    val listId: Long = Long.ZERO,
    val description: String = String.EMPTY,
    val checked: Boolean = false
)