package org.purchases.best.model.screens.home

import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState

class HomeScreenContract {

    data class State(
        val lists: List<ListInfo> = listOf(),
        val loadingLists: Boolean = true
    ) : ViewState

    sealed class Event : ViewEvent {
        data class DeleteListButtonPressedEvent(
            val listId: Long
        ) : Event()

        data object RequestListsEvent : Event()
    }
}