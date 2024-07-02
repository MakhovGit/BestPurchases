package org.purchases.best.database.model.interactors.database_interactor

sealed class SaveList : DatabaseInteractorMessages {
    data object Processing : SaveList()
    data class Success(
        val listWithPurchases: org.purchases.core.model.info.ListWithPurchasesInfo,
        val lists: List<org.purchases.core.model.info.ListInfo>
    ) : SaveList()

    data class Failure(
        val listWithPurchases: org.purchases.core.model.info.ListWithPurchasesInfo,
        val error: Throwable
    ) : SaveList()
}