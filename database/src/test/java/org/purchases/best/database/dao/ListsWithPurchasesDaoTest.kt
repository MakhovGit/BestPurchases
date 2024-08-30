package org.purchases.best.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.purchases.best.database.data.room.LocalDatabase
import org.purchases.best.database.data.room.dao.ListDao
import org.purchases.best.database.data.room.dao.ListsWithPurchasesDao
import org.purchases.best.database.data.room.dao.PurchaseDao
import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ListsWithPurchasesDaoTest {
    private val listEntity = ListEntity(
        listId = LIST_ID,
        listName = LIST_NAME
    )
    private val purchaseEntity = PurchaseEntity(
        purchaseId = PURCHASE_ID,
        extListId = PURCHASE_EXT_LIST_ID,
        purchaseInfo = PURCHASE_INFO,
        checked = PURCHASE_CHECKED
    )
    private lateinit var db: LocalDatabase
    private lateinit var listDao: ListDao
    private lateinit var purchaseDao: PurchaseDao
    private lateinit var listWithPurchaseDao: ListsWithPurchasesDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        listDao = db.getListDao()
        purchaseDao = db.getPurchaseDao()
        listWithPurchaseDao = db.getListWithPurchasesDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun `Test item can be correctly read from database`() = runTest {
        // Act
        listDao.saveList(listEntity)
        purchaseDao.insertPurchase(purchaseEntity)
        val result = listWithPurchaseDao.getListWithPurchases(LIST_ID)

        // Assert
        assertEquals(listEntity, result.list)
        assertEquals(purchaseEntity, result.purchases.first())
    }

    companion object {
        // listEntity
        private const val LIST_ID = 1L
        private const val LIST_NAME = "TEST"

        // purchaseEntity
        private const val PURCHASE_ID = 1L
        private const val PURCHASE_EXT_LIST_ID = 1L
        private const val PURCHASE_INFO = "TEST"
        private const val PURCHASE_CHECKED = false
    }

}