package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.ListWithPurchasesInfo

sealed class RequestListWithPurchases : DatabaseInteractorMessages {
    data object Processing : RequestListWithPurchases()
    data class Success(
        val listWithPurchases: ListWithPurchasesInfo
    ) : RequestListWithPurchases()

    data class Failure(
        val listId: Long,
        val error: Throwable
    ) : RequestListWithPurchases()
}