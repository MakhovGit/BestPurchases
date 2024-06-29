package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.PurchaseInfo

sealed class TogglePurchase : DatabaseInteractorMessages {
    data object Processing : TogglePurchase()
    data class Success(
        val purchase: PurchaseInfo
    ) : TogglePurchase()

    data class Failure(
        val purchaseId: Long,
        val error: Throwable
    ) : TogglePurchase()
}
