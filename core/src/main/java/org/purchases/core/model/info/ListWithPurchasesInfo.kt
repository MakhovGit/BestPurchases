package org.purchases.core.model.info

import org.purchases.core.utils.EMPTY
import org.purchases.core.utils.ZERO

data class ListWithPurchasesInfo(
    val id: Long = Long.ZERO,
    val name: String = String.EMPTY,
    val purchasesNotChecked: List<PurchaseInfo> = listOf(),
    val purchasesChecked: List<PurchaseInfo> = listOf()
)