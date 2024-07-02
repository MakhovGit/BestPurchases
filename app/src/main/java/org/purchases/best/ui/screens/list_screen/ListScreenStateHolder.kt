package org.purchases.best.ui.screens.list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.ui.base.state_holder.StateHolder

class ListScreenStateHolder(
    private val reducer: ListScreenReducer
) : StateHolder<DatabaseInteractorMessages> {
    var screenState by mutableStateOf(ListScreenContract.State())
        private set

    override fun update(result: DatabaseInteractorMessages) {
        screenState = reducer.reduce(screenState, result)
    }

}