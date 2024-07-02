package org.purchases.best.database.data.interactors

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import org.purchases.best.database.data.repository.LocalRepository
import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.database.model.interactors.database_interactor.DeleteList
import org.purchases.best.database.model.interactors.database_interactor.RequestListWithPurchases
import org.purchases.best.database.model.interactors.database_interactor.RequestLists
import org.purchases.best.database.model.interactors.database_interactor.SaveList
import org.purchases.best.database.model.interactors.database_interactor.SavePurchase
import org.purchases.best.database.model.interactors.database_interactor.TogglePurchase
import org.purchases.core.settings.MAIN_LOG_TAG
import org.purchases.core.utils.EMPTY
import org.purchases.core.utils.ONE

class DatabaseInteractor(
    private val repository: LocalRepository
) {
    private val _outFlow: MutableSharedFlow<DatabaseInteractorMessages> =
        MutableSharedFlow(replay = Int.ONE)
    val outFlow = _outFlow.asSharedFlow()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, error ->
        val errorMessage = buildString() {
            append(BASE_ERROR_MESSAGE)
            append(" In ")
            append(
                when (context.job) {
                    requestListsJob -> "requestListsJob"
                    saveListJob -> "saveListJob"
                    deleteListJob -> "deleteListJob"
                    requestListWithPurchasesJob -> "requestListWithPurchasesJob"
                    savePurchaseJob -> "savePurchaseJob"
                    togglePurchaseJob -> "togglePurchaseJob"
                    else -> String.EMPTY
                }
            )
            append(": ")
            append(error)
        }
        Log.e(MAIN_LOG_TAG, errorMessage)
        error.printStackTrace()
    }
    private val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)
    private var requestListsJob: Job? = null
    private var saveListJob: Job? = null
    private var deleteListJob: Job? = null
    private var requestListWithPurchasesJob: Job? = null
    private var savePurchaseJob: Job? = null
    private var togglePurchaseJob: Job? = null

    fun requestLists() {
        requestListsJob?.cancel()
        requestListsJob = mainScope.launch {
            _outFlow.emit(RequestLists.Processing)
            try {
                val lists = repository.getLists()
                _outFlow.emit(RequestLists.Success(lists = lists))
            } catch (error: Throwable) {
                _outFlow.emit(RequestLists.Failure(error = error))
            }
        }
    }

    fun saveList(listWithPurchases: org.purchases.core.model.info.ListWithPurchasesInfo) {
        saveListJob?.cancel()
        saveListJob = mainScope.launch {
            _outFlow.emit(SaveList.Processing)
            try {
                repository.saveList(listWithPurchases)
                val lists = repository.getLists()
                _outFlow.emit(
                    SaveList.Success(
                        listWithPurchases = listWithPurchases,
                        lists = lists
                    )
                )
            } catch (error: Throwable) {
                _outFlow.emit(
                    SaveList.Failure(
                        listWithPurchases = listWithPurchases,
                        error = error
                    )
                )
            }
        }
    }

    fun deleteList(listId: Long) {
        deleteListJob?.cancel()
        deleteListJob = mainScope.launch {
            _outFlow.emit(DeleteList.Processing)
            try {
                repository.deleteList(listId)
                val lists = repository.getLists()
                _outFlow.emit(DeleteList.Success(listId = listId, lists = lists))
            } catch (error: Throwable) {
                _outFlow.emit(DeleteList.Failure(listId = listId, error = error))
            }
        }
    }

    fun requestListWithPurchases(listId: Long) {
        requestListWithPurchasesJob?.cancel()
        requestListWithPurchasesJob = mainScope.launch {
            _outFlow.emit(RequestListWithPurchases.Processing)
            try {
                val listWithPurchases = repository.getListWithPurchases(listId)
                _outFlow.emit(RequestListWithPurchases.Success(listWithPurchases = listWithPurchases))
            } catch (error: Error) {
                _outFlow.emit(RequestListWithPurchases.Failure(listId = listId, error = error))
            }
        }
    }

    fun savePurchase(listId: Long, purchase: org.purchases.core.model.info.PurchaseInfo) {
        savePurchaseJob?.cancel()
        savePurchaseJob = mainScope.launch {
            _outFlow.emit(SavePurchase.Processing)
            try {
                repository.savePurchase(listId, purchase)
                _outFlow.emit(SavePurchase.Success(listId = listId, purchase = purchase))
            } catch (error: Throwable) {
                _outFlow.emit(SavePurchase.Failure(purchase = purchase, error = error))
            }
        }
    }

    fun togglePurchase(purchaseId: Long) {
        togglePurchaseJob?.cancel()
        togglePurchaseJob = mainScope.launch {
            _outFlow.emit(TogglePurchase.Processing)
            try {
                val purchase = repository.getPurchase(purchaseId)
                val isChecked = purchase.checked.not()
                repository.setPurchaseStatus(purchaseId, isChecked)
                _outFlow.emit(
                    TogglePurchase.Success(purchase = purchase)
                )
            } catch (error: Throwable) {
                _outFlow.emit(TogglePurchase.Failure(purchaseId = purchaseId, error = error))
            }
        }
    }

    companion object {
        private const val BASE_ERROR_MESSAGE = "Database interactor error!"
    }

}