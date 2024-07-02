package org.purchases.best.database.model.interactors.database_interactor

sealed class TogglePurchase : DatabaseInteractorMessages {
    data object Processing : TogglePurchase()
    data class Success(
        val purchase: org.purchases.core.model.info.PurchaseInfo
    ) : TogglePurchase()

    data class Failure(
        val purchaseId: Long,
        val error: Throwable
    ) : TogglePurchase()
}
