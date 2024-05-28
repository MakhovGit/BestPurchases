package org.purchases.best.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.purchases.best.intents.MainIntent
import org.purchases.best.model.intents.IntentContract
import org.purchases.best.model.screens.ScreenEvents
import org.purchases.best.model.screens.ScreenStates

@Composable
fun MainScreen() {
    val mainIntent: IntentContract<ScreenStates, ScreenEvents> = koinViewModel<MainIntent>()
    CompositionLocalProvider(
        androidx.lifecycle.compose.LocalLifecycleOwner
                provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
    ) {
        val screenStateFlow by mainIntent.screenStateFlow
            .collectAsStateWithLifecycle(initialValue = ScreenStates.EmptyScreenState)
        when (screenStateFlow) {
            is ScreenStates.HomeScreenState ->
                HomeScreen(screenStateFlow as ScreenStates.HomeScreenState, mainIntent)

            is ScreenStates.CreateListScreenState ->
                CreateListScreen(screenStateFlow as ScreenStates.CreateListScreenState, mainIntent)

            is ScreenStates.ListScreenState ->
                ListScreen(screenStateFlow as ScreenStates.ListScreenState, mainIntent)

            is ScreenStates.AddItemScreenState ->
                AddItemScreen(screenStateFlow as ScreenStates.AddItemScreenState, mainIntent)

            is ScreenStates.EmptyScreenState -> {}
        }
    }
}