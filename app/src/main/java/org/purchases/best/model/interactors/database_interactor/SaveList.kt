package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.ListWithPurchasesInfo

sealed class SaveList : DatabaseInteractorMessages {
    data object Processing : SaveList()
    data class Success(
        val listWithPurchases: ListWithPurchasesInfo,
        val lists: List<ListInfo>
    ) : SaveList()

    data class Failure(
        val listWithPurchases: ListWithPurchasesInfo,
        val error: Throwable
    ) : SaveList()
}