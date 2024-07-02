package org.purchases.best.database.model.interactors.database_interactor

sealed class RequestLists : DatabaseInteractorMessages {
    data object Processing : RequestLists()
    data class Success(
        val lists: List<org.purchases.core.model.info.ListInfo>
    ) : RequestLists()

    data class Failure(
        val error: Throwable
    ) : RequestLists()
}
