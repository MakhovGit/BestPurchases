package org.purchases.best.utils.db_mappers

import org.purchases.best.data.room.entities.ListEntity
import org.purchases.best.model.info.ListInfo

class ListMapper {
    fun map(listEntity: ListEntity) =
        ListInfo(
            id = listEntity.listId,
            title = listEntity.listName,
        )
}