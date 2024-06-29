package org.purchases.best.ui.screens.create_list_screen

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.screens.create_list.CreateListScreenContract
import org.purchases.best.ui.base.BaseViewModelAbstract

class CreateListScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModelAbstract<CreateListScreenContract.Event, CreateListScreenContract.State>() {
    private var handleEventJob: Job? = null
    override fun handleEvent(event: CreateListScreenContract.Event) {
        handleEventJob?.cancel()
        handleEventJob = mainScope.launch {
            when (event) {
                is CreateListScreenContract.Event.CreateNewListButtonPressedEvent ->
                    databaseInteractor.saveList(event.listWithPurchasesInfo)
            }
        }
    }
}