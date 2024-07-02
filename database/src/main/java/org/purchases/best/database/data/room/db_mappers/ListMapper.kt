package org.purchases.best.database.data.room.db_mappers

import org.purchases.best.database.data.room.entities.ListEntity

class ListMapper {
    fun map(listEntity: ListEntity) =
        org.purchases.core.model.info.ListInfo(
            id = listEntity.listId,
            title = listEntity.listName,
        )
}