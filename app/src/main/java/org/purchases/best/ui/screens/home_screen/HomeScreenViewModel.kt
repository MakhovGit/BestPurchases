package org.purchases.best.ui.screens.home_screen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.purchases.best.database.data.interactors.DatabaseInteractor
import org.purchases.best.database.model.interactors.database_interactor.DeleteList
import org.purchases.best.database.model.interactors.database_interactor.RequestLists
import org.purchases.best.model.screens.home.HomeScreenContract
import org.purchases.best.ui.base.BaseViewModelAbstract

class HomeScreenViewModel(
    private val databaseInteractor: DatabaseInteractor,
    private val stateHolder: HomeScreenStateHolder
) : BaseViewModelAbstract<HomeScreenContract.Event, HomeScreenContract.State>() {
    val screenState: HomeScreenContract.State
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
                    is RequestLists, is DeleteList ->
                        launch(Dispatchers.Main) { stateHolder.update(message) }
                }
            }
        }
    }

    override fun handleEvent(event: HomeScreenContract.Event) {
        handleEventJob?.cancel()
        handleEventJob = mainScope.launch {
            when (event) {
                is HomeScreenContract.Event.RequestListsEvent ->
                    databaseInteractor.requestLists()

                is HomeScreenContract.Event.DeleteListButtonPressedEvent ->
                    databaseInteractor.deleteList(event.listId)
            }
        }
    }
}