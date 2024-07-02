package org.purchases.core.model.info

import org.purchases.core.utils.EMPTY
import org.purchases.core.utils.ZERO

data class PurchaseInfo(
    val id: Long = Long.ZERO,
    val listId: Long = Long.ZERO,
    val description: String = String.EMPTY,
    val checked: Boolean = false
)