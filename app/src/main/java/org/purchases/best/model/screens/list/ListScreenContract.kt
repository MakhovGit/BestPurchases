package org.purchases.best.model.screens.list

import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState
import org.purchases.core.model.info.ListWithPurchasesInfo

class ListScreenContract {
    data class State(
        val isLoading: Boolean = false,
        val data: ListWithPurchasesInfo = ListWithPurchasesInfo(),
        val error: Throwable? = null
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