package org.purchases.best.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.interactors.DatabaseInteractorMessages
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.ui.base.BaseViewModel

class ListScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModel<ListScreenContract.Event, ListScreenContract.State>(databaseInteractor) {
    var screenState by mutableStateOf(ListScreenContract.State())
        private set
    override val modelName = "ListScreenViewModel"

    init {
        setDatabaseInteractorHandler()
    }

    private fun onDatabaseInteractorMessagesListsWithPurchases(
        scope: CoroutineScope,
        data: ListWithPurchasesInfo
    ) {
        scope.launch(context = Dispatchers.Main) {
            screenState = screenState.copy(data = data)
        }
    }

    private fun onRequestListWithPurchasesEvent(listId: Long) {
        databaseInteractor.requestListWithPurchases(listId)
    }

    private fun onTogglePurchaseEvent(purchaseId: Long) {
        databaseInteractor.togglePurchase(purchaseId)
    }

    private fun setDatabaseInteractorHandler() {
        mainScope.launch {
            databaseInteractor.outFlow.collect { message ->
                when (message) {
                    is DatabaseInteractorMessages.ListWithPurchases ->
                        onDatabaseInteractorMessagesListsWithPurchases(this, message.data)

                    else -> {}
                }
            }
        }
    }

    override fun handleEvent(event: ListScreenContract.Event) {
        mainScope.launch {
            when (event) {
                is ListScreenContract.Event.RequestListWithPurchases ->
                    onRequestListWithPurchasesEvent(event.listId)

                is ListScreenContract.Event.TogglePurchaseEvent ->
                    onTogglePurchaseEvent(event.purchaseId)
            }
        }
    }
}