package org.purchases.best.database.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.purchases.best.database.data.repository.LocalRepository
import org.purchases.best.database.data.repository.LocalRepositoryImpl
import org.purchases.best.database.data.room.dao.ListDao
import org.purchases.best.database.data.room.dao.ListsWithPurchasesDao
import org.purchases.best.database.data.room.dao.PurchaseDao
import org.purchases.best.database.data.room.db_mappers.ListMapper
import org.purchases.best.database.data.room.db_mappers.ListWithPurchasesMapper
import org.purchases.best.database.data.room.db_mappers.PurchaseMapper
import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.purchases.best.database.data.room.relations.ListWithPurchases
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core.utils.ONE

class LocalRepositoryTest {
    private val listEntity = ListEntity(
        listId = LIST_ENTITY_LIST_ID,
        listName = LIST_ENTITY_LIST_NAME
    )
    private val purchaseEntity1 = PurchaseEntity(
        purchaseId = PURCHASE1_ID,
        extListId = PURCHASE1_LIST_ID,
        purchaseInfo = PURCHASE1_DESCRIPTION,
        checked = PURCHASE1_CHECKED
    )
    private val purchaseEntity2 = PurchaseEntity(
        purchaseId = PURCHASE2_ID,
        extListId = PURCHASE2_LIST_ID,
        purchaseInfo = PURCHASE2_DESCRIPTION,
        checked = PURCHASE2_CHECKED
    )
    private val listWithPurchases = ListWithPurchases(
        list = listEntity,
        purchases = listOf(purchaseEntity1)
    )
    private val purchaseInfo1 = PurchaseInfo(
        id = PURCHASE1_ID,
        listId = PURCHASE1_LIST_ID,
        description = PURCHASE1_DESCRIPTION,
        checked = PURCHASE1_CHECKED
    )
    private val purchaseInfo2 = PurchaseInfo(
        id = PURCHASE2_ID,
        listId = PURCHASE2_LIST_ID,
        description = PURCHASE2_DESCRIPTION,
        checked = PURCHASE2_CHECKED
    )
    private val listWithPurchasesInfo = ListWithPurchasesInfo(
        id = LIST_WITH_PURCHASES_INFO_ID,
        name = LIST_WITH_PURCHASES_INFO_NAME,
        purchasesChecked = listOf(purchaseInfo2),
        purchasesNotChecked = listOf(purchaseInfo1)
    )
    private lateinit var listDao: ListDao
    private lateinit var listsWithPurchasesDao: ListsWithPurchasesDao
    private lateinit var purchaseDao: PurchaseDao
    private lateinit var listMapper: ListMapper
    private lateinit var listWithPurchasesMapper: ListWithPurchasesMapper
    private lateinit var purchaseMapper: PurchaseMapper
    private lateinit var repository: LocalRepository

    @Before
    fun setup() {
        listDao = mockk {
            coEvery { saveList(listEntity) } returns ID_1
            coEvery { deleteList(ID_1) } returns Unit
            coEvery { getLists() } returns listOf(listEntity)
        }
        purchaseDao = mockk {
            coEvery { insertPurchase(purchaseEntity1) } returns Unit
            coEvery { insertPurchases(listOf(purchaseEntity1)) } returns Unit
            coEvery { deletePurchase(ID_1) } returns Unit
            coEvery { getPurchase(ID_0) } returns purchaseEntity1
            coEvery { getPurchase(ID_1) } returns purchaseEntity2
            coEvery { setPurchaseStatus(ID_1, true) } returns Unit
        }
        listsWithPurchasesDao = mockk {
            coEvery { getListWithPurchases(ID_1) } returns listWithPurchases
        }
        listMapper = ListMapper()
        purchaseMapper = PurchaseMapper()
        listWithPurchasesMapper = ListWithPurchasesMapper(purchaseMapper = purchaseMapper)
        repository = LocalRepositoryImpl(
            listDao = listDao,
            listsWithPurchasesDao = listsWithPurchasesDao,
            purchaseDao = purchaseDao,
            listMapper = listMapper,
            listWithPurchasesMapper = listWithPurchasesMapper,
            purchaseMapper = purchaseMapper
        )
    }

    @Test
    fun `saveList() correctly saves ListWithPurchasesInfo to database`() = runTest {
        // Act
        repository.saveList(listWithPurchasesInfo)

        // Assert
        coVerify { listDao.saveList(listEntity) }
        coVerify { purchaseDao.insertPurchases(listOf(purchaseEntity1)) }
    }

    @Test
    fun `getLists() correctly loads data from database`() = runTest {
        // Act
        val result = repository.getLists()

        // Assert
        assertEquals(Int.ONE, result.size)
        assertEquals(LIST_WITH_PURCHASES_INFO_ID, result.first().id)
        assertEquals(LIST_WITH_PURCHASES_INFO_NAME, result.first().title)
        coVerify { listDao.getLists() }
    }

    @Test
    fun `deleteList() correctly deletes data from database`() = runTest {
        // Act
        repository.deleteList(LIST_WITH_PURCHASES_INFO_ID)

        // Assert
        coVerify { listDao.deleteList(LIST_WITH_PURCHASES_INFO_ID) }
        coVerify { purchaseDao.deletePurchase(LIST_WITH_PURCHASES_INFO_ID) }
    }

    @Test
    fun `getPurchase() correctly loads data from database`() = runTest {
        // Act
        val result = repository.getPurchase(ID_0)

        // Assert
        assertEquals(purchaseInfo1, result)
        coVerify { purchaseDao.getPurchase(ID_0) }
    }

    @Test
    fun `setPurchaseStatus() correctly changes purchase status in database`() = runTest {
        // Act
        repository.setPurchaseStatus(ID_1, true)

        // Assert
        coVerify { purchaseDao.setPurchaseStatus(ID_1, true) }
    }

    @Test
    fun `savePurchase() correctly saves purchase to database`() = runTest {
        // Act
        repository.savePurchase(ID_1, purchaseInfo1)

        // Assert
        coVerify { purchaseDao.insertPurchase(purchaseEntity1) }
    }

    companion object {
        // listEntity
        private const val LIST_ENTITY_LIST_ID = 1L
        private const val LIST_ENTITY_LIST_NAME = "NAME"

        // purchaseEntity1, purchaseInfo1
        private const val PURCHASE1_ID = 0L
        private const val PURCHASE1_LIST_ID = 1L
        private const val PURCHASE1_DESCRIPTION = "PURCHASE1"
        private const val PURCHASE1_CHECKED = false

        // purchaseEntity2, purchaseInfo2
        private const val PURCHASE2_ID = 1L
        private const val PURCHASE2_LIST_ID = 1L
        private const val PURCHASE2_DESCRIPTION = "PURCHASE2"
        private const val PURCHASE2_CHECKED = true

        // listWithPurchasesInfo
        private const val LIST_WITH_PURCHASES_INFO_ID = 1L
        private const val LIST_WITH_PURCHASES_INFO_NAME = "NAME"

        // Others
        private const val ID_0 = 0L
        private const val ID_1 = 1L
    }
}