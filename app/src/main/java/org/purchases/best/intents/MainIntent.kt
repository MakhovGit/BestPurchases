package org.purchases.best.intents

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.purchases.best.model.intents.IntentContract
import org.purchases.best.model.screens.ScreenEvents
import org.purchases.best.model.screens.ScreenStates
import org.purchases.best.settings.MAIN_LOG_TAG

class MainIntent : ViewModel(), IntentContract<ScreenStates, ScreenEvents> {
    private val _screenStateFlow: MutableStateFlow<ScreenStates> =
        MutableStateFlow(value = ScreenStates.EmptyScreenState)
    override val screenStateFlow = _screenStateFlow.asStateFlow()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        Log.e(MAIN_LOG_TAG, "MainIntent error!: ${error.message}.")
        error.printStackTrace()
    }
    private val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)

    init {
        handleEvent(ScreenEvents.InitialEvent)
    }

    override fun handleEvent(screenEvent: ScreenEvents) {
        mainScope.launch {
            when (screenEvent) {
                is ScreenEvents.InitialEvent -> {}
                is ScreenEvents.HomeScreenEvents -> {}
                is ScreenEvents.CreateListScreenEvents -> {}
                is ScreenEvents.ListScreenEvents -> {}
                is ScreenEvents.AddItemScreenEvents -> {}
            }
        }
    }

}