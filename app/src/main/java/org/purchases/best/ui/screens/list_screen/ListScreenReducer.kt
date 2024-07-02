package org.purchases.best.ui.screens.list_screen

import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.database.model.interactors.database_interactor.RequestListWithPurchases
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.ui.base.reducer.BaseReducerAbstract

class ListScreenReducer :
    BaseReducerAbstract<ListScreenContract.State, DatabaseInteractorMessages>() {

    private fun onRequestListWithPurchase(
        currentState: ListScreenContract.State,
        message: RequestListWithPurchases
    ): ListScreenContract.State {
        return when (message) {
            is RequestListWithPurchases.Processing -> currentState.copy(isLoading = true)
            is RequestListWithPurchases.Success -> currentState.copy(
                isLoading = false,
                data = message.listWithPurchases
            )

            is RequestListWithPurchases.Failure -> currentState.copy(
                isLoading = false,
                error = message.error
            )
        }
    }

    override fun reduce(
        currentState: ListScreenContract.State,
        result: DatabaseInteractorMessages
    ): ListScreenContract.State {
        return when (result) {
            is RequestListWithPurchases -> onRequestListWithPurchase(currentState, result)
            else -> throw RuntimeException("$ERROR_MESSAGE $result")
        }
    }

}