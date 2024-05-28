package org.purchases.best.model.screens

import org.purchases.best.model.info.CardInfo
import org.purchases.best.model.info.PurchaseInfo

sealed interface ScreenStates {
    data object EmptyScreenState : ScreenStates
    data class HomeScreenState(
        val titles: List<CardInfo>
    ) : ScreenStates

    data object CreateListScreenState : ScreenStates
    data class ListScreenState(
        val checkedPurchaseList: List<PurchaseInfo>,
        val uncheckedPurchaseList: List<PurchaseInfo>
    ) : ScreenStates

    data class AddItemScreenState(
        val listName: String,
        val item: String
    ) : ScreenStates
}