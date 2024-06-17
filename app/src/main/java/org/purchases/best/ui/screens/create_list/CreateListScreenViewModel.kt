package org.purchases.best.ui.screens.create_list

import kotlinx.coroutines.launch
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.screens.create_list.CreateListScreenContract
import org.purchases.best.ui.base.BaseViewModel

class CreateListScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModel<CreateListScreenContract.Event, CreateListScreenContract.State>(databaseInteractor) {
    override val modelName = "CreateListScreenViewModel"

    private fun onCreateNewListButtonPressedEvent(listWithPurchasesInfo: ListWithPurchasesInfo) {
        mainScope.launch {
            databaseInteractor.saveList(listWithPurchasesInfo)
        }
    }

    override fun handleEvent(event: CreateListScreenContract.Event) {
        when (event) {
            is CreateListScreenContract.Event.CreateNewListButtonPressedEvent ->
                onCreateNewListButtonPressedEvent(event.listWithPurchasesInfo)
        }
    }
}