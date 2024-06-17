package org.purchases.best.ui.screens.add_item

import kotlinx.coroutines.launch
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.model.screens.add_item.AddItemScreenContract
import org.purchases.best.ui.base.BaseViewModel

class AddItemScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModel<AddItemScreenContract.Event, AddItemScreenContract.State>(databaseInteractor) {
    override val modelName = "AddItemScreenViewModel"

    private fun onAddItemButtonPressedEvent(listId: Long, purchase: PurchaseInfo) {
        databaseInteractor.savePurchase(listId, purchase)
    }

    override fun handleEvent(event: AddItemScreenContract.Event) {
        mainScope.launch {
            when (event) {
                is AddItemScreenContract.Event.AddItemButtonPressedEvent ->
                    onAddItemButtonPressedEvent(event.listId, event.purchase)
            }
        }
    }
}