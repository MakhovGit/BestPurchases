package org.purchases.create_list_screen.ui.create_list_screen

import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core_ui.interfaces.ViewEvent
import org.purchases.core_ui.interfaces.ViewState

class CreateListScreenContract {
    data object State : ViewState

    sealed class Event : ViewEvent {
        data class CreateNewListButtonPressedEvent(
            val listWithPurchasesInfo: ListWithPurchasesInfo
        ) : Event()
    }
}