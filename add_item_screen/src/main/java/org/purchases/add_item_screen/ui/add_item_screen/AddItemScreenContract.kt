package org.purchases.add_item_screen.ui.add_item_screen

import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core_ui.interfaces.ViewEvent
import org.purchases.core_ui.interfaces.ViewState

class AddItemScreenContract {
    data object State : ViewState

    sealed class Event : ViewEvent {
        data class AddItemButtonPressedEvent(
            val listId: Long,
            val purchase: PurchaseInfo
        ) : Event()
    }

}