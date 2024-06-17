package org.purchases.best.model.info

import org.purchases.best.utils.EMPTY
import org.purchases.best.utils.ZERO

data class ListWithPurchasesInfo(
    val id: Long = Long.ZERO,
    val name: String = String.EMPTY,
    val purchasesNotChecked: List<PurchaseInfo> = listOf(),
    val purchasesChecked: List<PurchaseInfo> = listOf()
)