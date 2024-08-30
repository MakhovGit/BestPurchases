package org.purchases.best.database.mappers

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.purchases.best.database.data.room.db_mappers.ListMapper
import org.purchases.best.database.data.room.entities.ListEntity

class ListMapperTest {
    private val listEntity = ListEntity(
        listId = LIST_ENTITY_LIST_ID,
        listName = LIST_ENTITY_LIST_NAME
    )
    private val listMapper = ListMapper()

    @Test
    fun `map() correctly maps ListEntity to ListInfo`() {
        // Act
        val listInfo = listMapper.map(listEntity)

        // Assert
        assertEquals(listEntity.listId, listInfo.id)
        assertEquals(listEntity.listName, listInfo.title)
    }

    companion object {
        // listEntity
        private const val LIST_ENTITY_LIST_ID = 1L
        private const val LIST_ENTITY_LIST_NAME = "TEST"
    }
}