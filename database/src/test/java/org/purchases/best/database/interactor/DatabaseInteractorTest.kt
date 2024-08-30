package org.purchases.best.database.interactor

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.purchases.best.database.data.interactors.DatabaseInteractor
import org.purchases.best.database.data.repository.LocalRepository
import org.purchases.best.database.model.interactors.database_interactor.DatabaseInteractorMessages
import org.purchases.best.database.model.interactors.database_interactor.DeleteList
import org.purchases.best.database.model.interactors.database_interactor.RequestListWithPurchases
import org.purchases.best.database.model.interactors.database_interactor.RequestLists
import org.purchases.best.database.model.interactors.database_interactor.SaveList
import org.purchases.best.database.model.interactors.database_interactor.SavePurchase
import org.purchases.best.database.model.interactors.database_interactor.TogglePurchase
import org.purchases.core.model.info.ListInfo
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core.utils.ONE

class DatabaseInteractorTest {
    private val list1 = ListInfo(
        id = LIST1_ID,
        title = LIST1_TITLE
    )
    private val list2 = ListWithPurchasesInfo(
        id = LIST2_ID,
        name = LIST2_NAME
    )
    private val purchase = PurchaseInfo(
        id = PURCHASE_ID,
        listId = PURCHASE_LIST_ID,
        description = PURCHASE_DESCRIPTION,
        checked = PURCHASE_CHECKED
    )
    private lateinit var repositoryMock: LocalRepository
    private lateinit var databaseInteractor: DatabaseInteractor

    @Before
    fun setup() {
        repositoryMock = mockk<LocalRepository> {
            coEvery { getLists() } returns listOf(list1)
            coEvery { saveList(list2) } returns Unit
            coEvery { getListWithPurchases(ID_1) } returns list2
            coEvery { getPurchase(ID_1) } returns purchase
            coEvery { savePurchase(ID_1, purchase) } returns Unit
            coEvery { setPurchaseStatus(ID_1, purchase.checked.not()) } returns Unit
            coEvery { deleteList(ID_1) } returns Unit
        }
        databaseInteractor = DatabaseInteractor(repositoryMock)
    }

    @Test
    fun `requestLists() correctly uses repository when loads data from it`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is RequestLists.Processing)
                    assertTrue(messages[SECOND_INDEX] is RequestLists.Success)
                    assertEquals(
                        list1,
                        (messages[SECOND_INDEX] as RequestLists.Success).lists.first()
                    )
                    coVerify { repositoryMock.getLists() }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.requestLists()
    }

    @Test
    fun `saveList() correctly uses repository when saves data`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is SaveList.Processing)
                    assertTrue(messages[SECOND_INDEX] is SaveList.Success)
                    assertEquals(
                        list2,
                        (messages[SECOND_INDEX] as SaveList.Success).listWithPurchases
                    )
                    coVerify { repositoryMock.saveList(list2) }
                    coVerify { repositoryMock.getLists() }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.saveList(list2)
    }

    @Test
    fun `deleteList() correctly uses repository when deletes list`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is DeleteList.Processing)
                    assertTrue(messages[SECOND_INDEX] is DeleteList.Success)
                    assertEquals(
                        list1,
                        (messages[SECOND_INDEX] as DeleteList.Success).lists.first()
                    )
                    coVerify { repositoryMock.deleteList(ID_1) }
                    coVerify { repositoryMock.getLists() }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.deleteList(ID_1)
    }

    @Test
    fun `requestListWithPurchases() correctly uses repository when loads data`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is RequestListWithPurchases.Processing)
                    assertTrue(messages[SECOND_INDEX] is RequestListWithPurchases.Success)
                    assertEquals(
                        list2,
                        (messages[SECOND_INDEX] as RequestListWithPurchases.Success).listWithPurchases
                    )
                    coVerify { repositoryMock.getListWithPurchases(ID_1) }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.requestListWithPurchases(ID_1)
    }

    @Test
    fun `savePurchase() correctly uses repository when saves purchase data`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is SavePurchase.Processing)
                    assertTrue(messages[SECOND_INDEX] is SavePurchase.Success)
                    assertEquals(ID_1, (messages[SECOND_INDEX] as SavePurchase.Success).listId)
                    assertEquals(
                        purchase,
                        (messages[SECOND_INDEX] as SavePurchase.Success).purchase
                    )
                    coVerify { repositoryMock.savePurchase(ID_1, purchase) }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.savePurchase(ID_1, purchase)
    }

    @Test
    fun `togglePurchase() correctly uses repository when changes purchase status`() = runTest {
        val messages = mutableListOf<DatabaseInteractorMessages>()
        launch(Dispatchers.Unconfined) {
            databaseInteractor.outFlow.collectIndexed { index, message ->
                messages.add(message)
                if (index == Int.ONE) {
                    // Assert
                    assertEquals(EXPECTED_RESULTS, messages.size)
                    assertTrue(messages[FIRST_INDEX] is TogglePurchase.Processing)
                    assertTrue(messages[SECOND_INDEX] is TogglePurchase.Success)
                    assertEquals(
                        purchase,
                        (messages[SECOND_INDEX] as TogglePurchase.Success).purchase
                    )
                    coVerify { repositoryMock.getPurchase(ID_1) }
                    coVerify { repositoryMock.setPurchaseStatus(ID_1, purchase.checked.not()) }
                    cancel()
                }
            }
        }
        // Act
        databaseInteractor.togglePurchase(ID_1)
    }

    companion object {
        // list1
        private const val LIST1_ID = 1L
        private const val LIST1_TITLE = "LIST_1"

        // list2
        private const val LIST2_ID = 1L
        private const val LIST2_NAME = "LIST_2"

        // purchase
        private const val PURCHASE_ID = 1L
        private const val PURCHASE_LIST_ID = 2L
        private const val PURCHASE_DESCRIPTION = "PURCHASE"
        private const val PURCHASE_CHECKED = true

        // Others
        private const val EXPECTED_RESULTS = 2
        private const val FIRST_INDEX = 0
        private const val SECOND_INDEX = 1
        private const val ID_1 = 1L

    }
}