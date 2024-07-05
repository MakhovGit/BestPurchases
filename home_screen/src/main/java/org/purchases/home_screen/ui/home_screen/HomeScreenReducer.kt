package org.purchases.home_screen.ui.home_screen

import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.database.model.interactors.database_interactor.DeleteList
import org.purchases.best.database.model.interactors.database_interactor.RequestLists
import org.purchases.core_ui.ui.base.reducer.BaseReducerAbstract

class HomeScreenReducer :
    BaseReducerAbstract<HomeScreenContract.State, DatabaseInteractorMessages>() {

    private fun onRequestLists(
        currentState: HomeScreenContract.State,
        message: RequestLists
    ): HomeScreenContract.State {
        return when (message) {
            is RequestLists.Processing -> currentState.copy(isLoading = true)
            is RequestLists.Success -> currentState.copy(isLoading = false, data = message.lists)
            is RequestLists.Failure -> currentState.copy(isLoading = false, error = message.error)
        }
    }

    private fun onDeleteList(
        currentState: HomeScreenContract.State,
        message: DeleteList
    ): HomeScreenContract.State {
        return when (message) {
            is DeleteList.Processing -> currentState.copy(isLoading = true)
            is DeleteList.Success -> currentState.copy(isLoading = false, data = message.lists)
            is DeleteList.Failure -> currentState.copy(isLoading = false, error = message.error)
        }
    }

    override fun reduce(
        currentState: HomeScreenContract.State,
        result: DatabaseInteractorMessages
    ): HomeScreenContract.State {
        return when (result) {
            is RequestLists -> onRequestLists(currentState, result)
            is DeleteList -> onDeleteList(currentState, result)
            else -> throw RuntimeException("$ERROR_MESSAGE $result")
        }
    }
}