package org.purchases.best.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.purchases.best.data.interactors.DatabaseInteractor
import org.purchases.best.model.screens.ViewEvent
import org.purchases.best.model.screens.ViewState
import org.purchases.best.settings.MAIN_LOG_TAG

abstract class BaseViewModel<E : ViewEvent, S : ViewState>(
    private val databaseInteractor: DatabaseInteractor
) : ViewModel() {
    abstract val modelName: String
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        Log.e(MAIN_LOG_TAG, "$modelName error!: ${error.message}.")
        error.printStackTrace()
    }
    val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)

    abstract fun handleEvent(event: E)
}