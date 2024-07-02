package org.purchases.best.database.model.interactors.database_interactor

sealed class RequestListWithPurchases : DatabaseInteractorMessages {
    data object Processing : RequestListWithPurchases()
    data class Success(
        val listWithPurchases: org.purchases.core.model.info.ListWithPurchasesInfo
    ) : RequestListWithPurchases()

    data class Failure(
        val listId: Long,
        val error: Throwable
    ) : RequestListWithPurchases()
}