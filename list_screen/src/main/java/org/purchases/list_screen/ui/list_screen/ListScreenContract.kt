package org.purchases.list_screen.ui.list_screen

import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core_ui.interfaces.ViewEvent
import org.purchases.core_ui.interfaces.ViewState

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