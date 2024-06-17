package org.purchases.best.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.interactors.DatabaseInteractorMessages
import org.purchases.best.model.screens.home.HomeScreenContract
import org.purchases.best.ui.base.BaseViewModel

class HomeScreenViewModel(
    private val databaseInteractor: DatabaseInteractor
) : BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State>(databaseInteractor) {
    var screenState by mutableStateOf(HomeScreenContract.State())
        private set
    override val modelName = "HomeScreenViewModel"

    init {
        setDatabaseInteractorHandler()
    }

    private fun onDatabaseInteractorMessagesLists(
        scope: CoroutineScope,
        lists: List<ListInfo>
    ) {
        scope.launch(context = Dispatchers.Main) {
            screenState = screenState.copy(
                lists = lists,
                loadingLists = false
            )
        }
    }

    private fun onDatabaseInteractorMessagesListDeleted(listId: Long) {
        databaseInteractor.requestLists()
    }

    private fun setDatabaseInteractorHandler() {
        mainScope.launch {
            databaseInteractor.outFlow.collect { message ->
                when (message) {
                    is DatabaseInteractorMessages.Lists ->
                        onDatabaseInteractorMessagesLists(this, message.lists)

                    is DatabaseInteractorMessages.ListDeleted ->
                        onDatabaseInteractorMessagesListDeleted(message.listId)

                    else -> {}
                }
            }
        }
    }

    private fun onDeleteListButtonPressedEvent(listId: Long) {
        mainScope.launch {
            databaseInteractor.deleteList(listId)
        }
    }

    private fun requestLists() {
        mainScope.launch {
            databaseInteractor.requestLists()
        }
    }

    override fun handleEvent(event: HomeScreenContract.Event) {
        mainScope.launch {
            when (event) {
                is HomeScreenContract.Event.RequestListsEvent ->
                    requestLists()

                is HomeScreenContract.Event.DeleteListButtonPressedEvent ->
                    onDeleteListButtonPressedEvent(event.listId)
            }
        }
    }
}