package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.ListInfo

sealed class RequestLists : DatabaseInteractorMessages {
    data object Processing : RequestLists()
    data class Success(
        val lists: List<ListInfo>
    ) : RequestLists()

    data class Failure(
        val error: Throwable
    ) : RequestLists()
}
