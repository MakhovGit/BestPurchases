package org.purchases.best.database.model.interactors.database_interactor

sealed class SavePurchase : DatabaseInteractorMessages {
    data object Processing : SavePurchase()
    data class Success(
        val listId: Long,
        val purchase: org.purchases.core.model.info.PurchaseInfo
    ) : SavePurchase()

    data class Failure(
        val purchase: org.purchases.core.model.info.PurchaseInfo,
        val error: Throwable
    ) : SavePurchase()
}
