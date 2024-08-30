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
import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.core.utils.ZERO
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ListDaoTest {
    private val listEntity = ListEntity(
        listId = LIST_ENTITY_LIST_ID,
        listName = LIST_ENTITY_LIST_NAME
    )
    private lateinit var db: LocalDatabase
    private lateinit var listDao: ListDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        listDao = db.getListDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun `Test item correctly inserts to database`() = runTest {
        // Act
        listDao.saveList(listEntity)
        val result = listDao.getLists()

        // Assert
        assertEquals(listEntity, result?.first())
    }

    @Test
    fun `Test item correctly removes from database`() = runTest {
        // Act
        listDao.saveList(listEntity)
        listDao.deleteList(listId = LIST_ENTITY_LIST_ID)
        val result = listDao.getLists()

        // Assert
        assertEquals(Int.ZERO, result?.size)
    }

    companion object {
        // listEntity
        private const val LIST_ENTITY_LIST_ID = 1L
        private const val LIST_ENTITY_LIST_NAME = "TEST"
    }
}