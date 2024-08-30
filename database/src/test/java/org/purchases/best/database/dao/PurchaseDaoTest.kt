package org.purchases.best.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.purchases.best.database.data.room.LocalDatabase
import org.purchases.best.database.data.room.dao.PurchaseDao
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PurchaseDaoTest {
    private val purchaseEntity = PurchaseEntity(
        purchaseId = PURCHASE_ENTITY_PURCHASE_ID,
        extListId = PURCHASE_ENTITY_EXT_LIST_ID,
        purchaseInfo = PURCHASE_ENTITY_PURCHASE_INFO,
        checked = PURCHASE_ENTITY_CHECKED
    )
    private val listOfPurchases = listOf(purchaseEntity)
    private lateinit var db: LocalDatabase
    private lateinit var purchaseDao: PurchaseDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        purchaseDao = db.getPurchaseDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun `Test item correctly inserts to database`() = runTest {
        // Act
        purchaseDao.insertPurchase(purchaseEntity)
        val result = purchaseDao.getPurchase(purchaseId = PURCHASE_ENTITY_PURCHASE_ID)

        // Assert
        assertEquals(purchaseEntity, result)
    }

    @Test
    fun `Test item correctly removes from database`() = runTest {
        // Act
        purchaseDao.deletePurchase(listId = PURCHASE_ENTITY_EXT_LIST_ID)
        val result = purchaseDao.getPurchase(purchaseId = PURCHASE_ENTITY_PURCHASE_ID)

        // Assert
        assertNull(result)
    }

    @Test
    fun `Test list of items correctly inserts to database`() = runTest {
        // Act
        purchaseDao.insertPurchases(listOfPurchases)
        val result = purchaseDao.getPurchase(purchaseId = PURCHASE_ENTITY_PURCHASE_ID)

        // Assert
        assertEquals(purchaseEntity, result)
    }

    @Test
    fun `Test checked state correctly changes`() = runTest {
        // Act
        purchaseDao.insertPurchase(purchaseEntity)
        purchaseDao.setPurchaseStatus(purchaseId = PURCHASE_ENTITY_PURCHASE_ID, isChecked = true)
        val result = purchaseDao.getPurchase(purchaseId = PURCHASE_ENTITY_PURCHASE_ID)

        // Assert
        assertEquals(true, result?.checked)
    }

    companion object {
        // purchaseEntity
        private const val PURCHASE_ENTITY_PURCHASE_ID = 1L
        private const val PURCHASE_ENTITY_EXT_LIST_ID = 1L
        private const val PURCHASE_ENTITY_PURCHASE_INFO = "TEST"
        private const val PURCHASE_ENTITY_CHECKED = false

    }

}