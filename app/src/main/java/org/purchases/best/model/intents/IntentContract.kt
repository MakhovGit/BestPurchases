package org.purchases.best.model.intents

import kotlinx.coroutines.flow.StateFlow

interface IntentContract<T, U> {
    val screenStateFlow: StateFlow<T>
    fun handleEvent(screenEvent: U)
}