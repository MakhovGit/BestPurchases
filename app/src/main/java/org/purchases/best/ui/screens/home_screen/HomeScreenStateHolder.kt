package org.purchases.best.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.purchases.best.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.model.screens.home.HomeScreenContract
import org.purchases.best.ui.base.state_holder.StateHolder

class HomeScreenStateHolder(
    private val reducer: HomeScreenReducer
) : StateHolder<DatabaseInteractorMessages> {
    var screenState by mutableStateOf(HomeScreenContract.State())
        private set

    override fun update(result: DatabaseInteractorMessages) {
        screenState = reducer.reduce(screenState, result)
    }

}