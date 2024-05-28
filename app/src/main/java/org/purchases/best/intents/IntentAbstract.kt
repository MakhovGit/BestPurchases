package org.purchases.best.intents

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.purchases.best.model.intents.IntentContract
import org.purchases.best.model.screens.ScreenEvents
import org.purchases.best.model.screens.ScreenStates

abstract class IntentAbstract: IntentContract<ScreenStates, ScreenEvents> {
    private val _screenStateFlow: MutableStateFlow<ScreenStates> =
        MutableStateFlow(value = ScreenStates.EmptyScreenState)
    override val screenStateFlow = _screenStateFlow.asStateFlow()

    override fun handleEvent(screenEvent: ScreenEvents) {}
}