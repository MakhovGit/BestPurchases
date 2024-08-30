package org.purchases.best.database.mappers

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.purchases.best.database.data.room.db_mappers.PurchaseMapper
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.purchases.core.model.info.PurchaseInfo

class PurchaseMapperTest {
    private val purchaseInfo = PurchaseInfo(
        id = PURCHASE_INFO_ID,
        listId = PURCHASE_INFO_LIST_ID,
        description = PURCHASE_INFO_DESCRIPTION,
        checked = PURCHASE_INFO_CHECKED
    )
    private val purchaseEntity = PurchaseEntity(
        purchaseId = PURCHASE_ENTITY_PURCHASE_ID,
        extListId = PURCHASE_ENTITY_EXT_LIST_ID,
        purchaseInfo = PURCHASE_ENTITY_PURCHASE_INFO,
        checked = PURCHASE_ENTITY_CHECKED
    )
    private val listId = LIST_ID
    private val purchaseMapper = PurchaseMapper()

    @Test
    fun `map() correctly maps PurchaseInfo to PurchaseEntity`() {
        // Act
        val purchaseEntity = purchaseMapper.map(purchaseInfo, listId)

        // Assert
        assertEquals(listId, purchaseEntity.extListId)
        assertEquals(purchaseInfo.description, purchaseEntity.purchaseInfo)
        assertEquals(purchaseInfo.checked, purchaseEntity.checked)
    }

    @Test
    fun `map() correctly maps PurchaseEntity to PurchaseInfo`() {
        // Act
        val purchaseInfo = purchaseMapper.map(purchaseEntity)

        // Assert
        assertEquals(purchaseEntity.purchaseId, purchaseInfo.id)
        assertEquals(purchaseEntity.extListId, purchaseInfo.listId)
        assertEquals(purchaseEntity.purchaseInfo, purchaseInfo.description)
        assertEquals(purchaseEntity.checked, purchaseInfo.checked)
    }

    companion object {
        // purchaseInfo
        private const val PURCHASE_INFO_ID = 1L
        private const val PURCHASE_INFO_LIST_ID = 0L
        private const val PURCHASE_INFO_DESCRIPTION = "DESCRIPTION"
        private const val PURCHASE_INFO_CHECKED = true

        // purchaseEntity
        private const val PURCHASE_ENTITY_PURCHASE_ID = 1L
        private const val PURCHASE_ENTITY_EXT_LIST_ID = 2L
        private const val PURCHASE_ENTITY_PURCHASE_INFO = "DESCRIPTION"
        private const val PURCHASE_ENTITY_CHECKED = true

        // Others
        private const val LIST_ID = 1L
    }
}