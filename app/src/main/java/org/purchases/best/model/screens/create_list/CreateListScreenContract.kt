package org.purchases.best.model.screens.create_list

import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState
import org.purchases.core.model.info.ListWithPurchasesInfo

class CreateListScreenContract {
    data object State : ViewState

    sealed class Event : ViewEvent {
        data class CreateNewListButtonPressedEvent(
            val listWithPurchasesInfo: ListWithPurchasesInfo
        ) : Event()
    }
}