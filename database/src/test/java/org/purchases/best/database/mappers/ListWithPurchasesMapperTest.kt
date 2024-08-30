package org.purchases.best.database.mappers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.purchases.best.database.data.room.db_mappers.ListWithPurchasesMapper
import org.purchases.best.database.data.room.db_mappers.PurchaseMapper
import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.best.database.data.room.entities.PurchaseEntity
import org.purchases.best.database.data.room.relations.ListWithPurchases
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core.utils.ONE
import org.purchases.core.utils.ZERO

class ListWithPurchasesMapperTest {
    private val listEntity = ListEntity(
        listId = LIST_ENTITY_LIST_ID,
        listName = LIST_ENTITY_LIST_NAME
    )
    private val purchaseEntity = PurchaseEntity(
        purchaseId = PURCHASE_ENTITY_PURCHASE_ID,
        extListId = PURCHASE_ENTITY_EXT_LIST_ID,
        purchaseInfo = PURCHASE_ENTITY_PURCHASE_INFO,
        checked = PURCHASE_ENTITY_CHECKED
    )
    private val purchaseInfo = PurchaseInfo(
        id = PURCHASE_INFO_ID,
        listId = PURCHASE_INFO_LIST_ID,
        description = PURCHASE_INFO_DESCRIPTION,
        checked = PURCHASE_INFO_CHECKED
    )
    private val listOfPurchases = listOf(purchaseEntity)
    private val listWithPurchases = ListWithPurchases(
        list = listEntity,
        purchases = listOfPurchases
    )
    private val listWithPurchasesInfo = ListWithPurchasesInfo(
        id = LIST_WITH_PURCHASES_INFO_ID,
        name = LIST_WITH_PURCHASES_INFO_NAME
    )
    private lateinit var purchaseMapperMock: PurchaseMapper
    private lateinit var listWithPurchasesMapper: ListWithPurchasesMapper

    @Before
    fun setup() {
        purchaseMapperMock = mockk<PurchaseMapper> {
            every { map(purchaseEntity) } returns purchaseInfo
        }
        listWithPurchasesMapper = ListWithPurchasesMapper(purchaseMapperMock)
    }

    @Test
    fun `map() correctly maps ListWithPurchases to ListWithPurchasesInfo`() {
        // Act
        val listWithPurchasesInfo = listWithPurchasesMapper.map(listWithPurchases)

        // Assert
        assertEquals(Int.ONE, listWithPurchasesInfo.purchasesChecked.size)
        assertEquals(Int.ZERO, listWithPurchasesInfo.purchasesNotChecked.size)
        assertEquals(listEntity.listId, listWithPurchasesInfo.id)
        assertEquals(listEntity.listName, listWithPurchasesInfo.name)
        assertEquals(purchaseInfo.id, listWithPurchasesInfo.purchasesChecked.first().id)
        assertEquals(purchaseInfo.listId, listWithPurchasesInfo.purchasesChecked.first().listId)
        assertEquals(
            purchaseInfo.description,
            listWithPurchasesInfo.purchasesChecked.first().description
        )
        assertEquals(purchaseInfo.checked, listWithPurchasesInfo.purchasesChecked.first().checked)
        verify { purchaseMapperMock.map(purchaseEntity) }
    }

    @Test
    fun `map() correctly maps ListWithPurchasesInfo to ListEntity`() {
        // Act
        val listEntity = listWithPurchasesMapper.map(listWithPurchasesInfo)

        // Assert
        assertEquals(listWithPurchasesInfo.id, listEntity.listId)
        assertEquals(listWithPurchasesInfo.name, listEntity.listName)
    }

    companion object {
        // listEntity
        private const val LIST_ENTITY_LIST_ID = 1L
        private const val LIST_ENTITY_LIST_NAME = "LIST_NAME"

        // purchaseEntity
        private const val PURCHASE_ENTITY_PURCHASE_ID = 1L
        private const val PURCHASE_ENTITY_EXT_LIST_ID = 1L
        private const val PURCHASE_ENTITY_PURCHASE_INFO = "PURCHASE_INFO"
        private const val PURCHASE_ENTITY_CHECKED = true

        // purchaseInfo
        private const val PURCHASE_INFO_ID = 1L
        private const val PURCHASE_INFO_LIST_ID = 1L
        private const val PURCHASE_INFO_DESCRIPTION = "DESCRIPTION"
        private const val PURCHASE_INFO_CHECKED = true

        // listWithPurchasesInfo
        private const val LIST_WITH_PURCHASES_INFO_ID = 1L
        private const val LIST_WITH_PURCHASES_INFO_NAME = "NAME"
    }
}