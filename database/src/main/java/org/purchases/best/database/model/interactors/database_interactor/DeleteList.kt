package org.purchases.best.database.model.interactors.database_interactor

sealed class DeleteList : DatabaseInteractorMessages {
    data object Processing : DeleteList()
    data class Success(
        val listId: Long,
        val lists: List<org.purchases.core.model.info.ListInfo>
    ) : DeleteList()

    data class Failure(
        val listId: Long,
        val error: Throwable
    ) : DeleteList()
}
