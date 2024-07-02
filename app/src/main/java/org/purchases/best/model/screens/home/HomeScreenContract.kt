package org.purchases.best.model.screens.home

import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState
import org.purchases.core.model.info.ListInfo

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