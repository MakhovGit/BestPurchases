package org.purchases.best.model.screens

import org.purchases.best.model.info.PurchaseInfo

sealed interface ScreenEvents {
    data object InitialEvent : ScreenEvents
    sealed interface HomeScreenEvents {
        data object NewListButtonPressedEvent : HomeScreenEvents, ScreenEvents
        data class DeleteListButtonPressedEvent(
            val listName: String
        ) : HomeScreenEvents, ScreenEvents
    }

    sealed interface CreateListScreenEvents {
        data class CreateNewListButtonPressedEvent(
            val listName: String,
            val purchases: List<PurchaseInfo>
        ) : CreateListScreenEvents, ScreenEvents

        data object CancelButtonPressedEvent : CreateListScreenEvents, ScreenEvents
    }

    sealed interface ListScreenEvents {
        data class AddItemButtonPressedEvent(
            val listName: String
        ) : ListScreenEvents, ScreenEvents

        data class CheckPurchaseEvent(
            val purchase: PurchaseInfo
        ) : ListScreenEvents, ScreenEvents

        data class UncheckPurchaseEvent(
            val purchase: PurchaseInfo
        ) : ListScreenEvents, ScreenEvents

        data object BackButtonPressedEvent : ListScreenEvents, ScreenEvents
    }

    sealed interface AddItemScreenEvents {
        data class AddItemButtonPressedEvent(
            val listName: String,
            val item: String
        ) : AddItemScreenEvents, ScreenEvents

        data object CancelButtonPressedEvent : AddItemScreenEvents, ScreenEvents
    }
}