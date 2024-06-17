package org.purchases.best.model.screens.list

import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState

class ListScreenContract {
    data class State(
        val data: ListWithPurchasesInfo = ListWithPurchasesInfo()
    ) : ViewState

    sealed class Event : ViewEvent {
        data class RequestListWithPurchases(
            val listId: Long
        ) : Event()

        data class TogglePurchaseEvent(
            val purchaseId: Long
        ) : Event()
    }
}