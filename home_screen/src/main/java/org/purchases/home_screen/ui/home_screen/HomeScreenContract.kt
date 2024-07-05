package org.purchases.home_screen.ui.home_screen

import org.purchases.core.model.info.ListInfo
import org.purchases.core_ui.interfaces.ViewEvent
import org.purchases.core_ui.interfaces.ViewState

class HomeScreenContract {

    data class State(
        val isLoading: Boolean = true,
        val data: List<ListInfo> = listOf(),
        val error: Throwable? = null
    ) : ViewState

    sealed class Event : ViewEvent {
        data class DeleteListButtonPressedEvent(
            val listId: Long
        ) : Event()

        data object RequestListsEvent : Event()
    }
}