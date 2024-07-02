package org.purchases.best.model.screens.add_item

import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState
import org.purchases.core.model.info.PurchaseInfo

class AddItemScreenContract {
    data object State : ViewState

    sealed class Event : ViewEvent {
        data class AddItemButtonPressedEvent(
            val listId: Long,
            val purchase: PurchaseInfo
        ) : Event()
    }

}