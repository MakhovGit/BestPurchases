package org.purchases.best.model.interactors.database_interactor

import org.purchases.best.model.info.ListInfo

sealed class DeleteList : DatabaseInteractorMessages {
    data object Processing : DeleteList()
    data class Success(
        val listId: Long,
        val lists: List<ListInfo>
    ) : DeleteList()

    data class Failure(
        val listId: Long,
        val error: Throwable
    ) : DeleteList()
}
