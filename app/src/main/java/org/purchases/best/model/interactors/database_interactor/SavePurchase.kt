package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.PurchaseInfo

sealed class SavePurchase : DatabaseInteractorMessages {
    data object Processing : SavePurchase()
    data class Success(
        val listId: Long,
        val purchase: PurchaseInfo
    ) : SavePurchase()

    data class Failure(
        val purchase: PurchaseInfo,
        val error: Throwable
    ) : SavePurchase()
}
