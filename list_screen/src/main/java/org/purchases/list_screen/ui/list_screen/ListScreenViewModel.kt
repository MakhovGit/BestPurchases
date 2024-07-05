package org.purchases.list_screen.ui.list_screen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.purchases.best.database.data.interactors.DatabaseInteractor
import org.purchases.best.database.model.interactors.database_interactor.RequestListWithPurchases
import org.purchases.core_ui.ui.base.BaseViewModelAbstract

class ListScreenViewModel(
    private val databaseInteractor: DatabaseInteractor,
    private val stateHolder: ListScreenStateHolder
) : BaseViewModelAbstract<ListScreenContract.Event, ListScreenContract.State>() {
    val screenState: ListScreenContract.State
        get() = stateHolder.screenState

    private var databaseInteractorHandlerJob: Job? = null
    private var handleEventJob: Job? = null

    init {
        setDatabaseInteractorHandler()
    }

    private fun setDatabaseInteractorHandler() {
        databaseInteractorHandlerJob?.cancel()
        databaseInteractorHandlerJob = mainScope.launch {
            databaseInteractor.outFlow.collect { message ->
                when (message) {
                    is RequestListWithPurchases ->
                        launch(Dispatchers.Main) { stateHolder.update(message) }
                }
            }
        }
    }

    override fun handleEvent(event: ListScreenContract.Event) {
        handleEventJob?.cancel()
        handleEventJob = mainScope.launch {
            when (event) {
                is ListScreenContract.Event.RequestListWithPurchases ->
                    databaseInteractor.requestListWithPurchases(event.listId)

                is ListScreenContract.Event.TogglePurchaseEvent ->
                    databaseInteractor.togglePurchase(event.purchaseId)
            }
        }
    }
}