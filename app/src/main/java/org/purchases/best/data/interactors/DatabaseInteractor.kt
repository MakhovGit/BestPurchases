package org.purchases.best.data.interactors

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.purchases.best.data.repository.LocalRepository
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.model.interactors.DatabaseInteractorMessages
import org.purchases.best.settings.MAIN_LOG_TAG
import org.purchases.best.utils.ONE

class DatabaseInteractor(
    private val repository: LocalRepository
) {
    private val _outFlow: MutableSharedFlow<DatabaseInteractorMessages> =
        MutableSharedFlow(replay = Int.ONE)
    val outFlow = _outFlow.asSharedFlow()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        Log.e(MAIN_LOG_TAG, "Database interactor error! $error")
        error.printStackTrace()
    }
    private val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)

    fun togglePurchase(purchaseId: Long) {
        mainScope.launch {
            _outFlow.emit(DatabaseInteractorMessages.Processing)
            val purchase = repository.getPurchase(purchaseId)
            val isChecked = purchase.checked.not()
            repository.setPurchaseStatus(purchaseId, isChecked)
            _outFlow.emit(
                (DatabaseInteractorMessages.PurchaseToggled(
                    purchaseInfo = purchase
                ))
            )
        }
    }

    fun requestLists() {
        mainScope.launch {
            _outFlow.emit(DatabaseInteractorMessages.Processing)
            val lists = repository.getLists()
            _outFlow.emit(DatabaseInteractorMessages.Lists(lists))
        }
    }

    fun requestListWithPurchases(listId: Long) {
        mainScope.launch {
            _outFlow.emit(DatabaseInteractorMessages.Processing)
            _outFlow.emit(
                DatabaseInteractorMessages.ListWithPurchases(
                    repository.getListWithPurchases(listId)
                )
            )
        }
    }

    fun saveList(listWithPurchasesInfo: ListWithPurchasesInfo) {
        mainScope.launch {
            _outFlow.emit(DatabaseInteractorMessages.Processing)
            repository.saveList(listWithPurchasesInfo)
            _outFlow.emit(DatabaseInteractorMessages.ListSaved(listWithPurchasesInfo.id))
        }
    }

    fun savePurchase(listId: Long, purchase: PurchaseInfo) {
        mainScope.launch {
            repository.savePurchase(listId, purchase)
        }
    }

    fun deleteList(listId: Long) {
        mainScope.launch {
            _outFlow.emit(DatabaseInteractorMessages.Processing)
            repository.deleteList(listId)
            _outFlow.emit(DatabaseInteractorMessages.ListDeleted(listId = listId))
        }
    }

}