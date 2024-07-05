package org.purchases.add_item_screen.ui.add_item_screen

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.purchases.best.database.data.interactors.DatabaseInteractor
import org.purchases.core_ui.ui.base.BaseViewModelAbstract

class AddItemScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModelAbstract<AddItemScreenContract.Event, AddItemScreenContract.State>() {
    private var handleEventJob: Job? = null
    override fun handleEvent(event: AddItemScreenContract.Event) {
        handleEventJob?.cancel()
        handleEventJob = mainScope.launch {
            when (event) {
                is AddItemScreenContract.Event.AddItemButtonPressedEvent ->
                    databaseInteractor.savePurchase(event.listId, event.purchase)
            }
        }
    }
}