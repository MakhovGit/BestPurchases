package org.purchases.best.database.data.room.db_mappers

import org.purchases.best.database.data.room.entities.ListEntity
import org.purchases.core.model.info.ListInfo

class ListMapper {
    fun map(listEntity: ListEntity) =
        ListInfo(
            id = listEntity.listId,
            title = listEntity.listName,
        )
}