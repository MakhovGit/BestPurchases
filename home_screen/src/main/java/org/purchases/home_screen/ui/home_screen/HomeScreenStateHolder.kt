package org.purchases.home_screen.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.core_ui.ui.base.state_holder.StateHolder

class HomeScreenStateHolder(
    private val reducer: HomeScreenReducer
) : StateHolder<DatabaseInteractorMessages> {
    var screenState by mutableStateOf(HomeScreenContract.State())
        private set

    override fun update(result: DatabaseInteractorMessages) {
        screenState = reducer.reduce(screenState, result)
    }

}