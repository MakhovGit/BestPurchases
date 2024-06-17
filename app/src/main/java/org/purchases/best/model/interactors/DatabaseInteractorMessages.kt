package org.purchases.best.model.interactors

import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo

sealed interface DatabaseInteractorMessages {
    data object Processing : DatabaseInteractorMessages
    data class Lists(
        val lists: List<ListInfo>
    ) : DatabaseInteractorMessages

    data class ListWithPurchases(
        val data: ListWithPurchasesInfo
    ) : DatabaseInteractorMessages

    data class ListDeleted(
        val listId: Long
    ) : DatabaseInteractorMessages

    data class ListSaved(
        val listId: Long
    ) : DatabaseInteractorMessages

    data class PurchaseToggled(
        val purchaseInfo: PurchaseInfo
    ) : DatabaseInteractorMessages
}