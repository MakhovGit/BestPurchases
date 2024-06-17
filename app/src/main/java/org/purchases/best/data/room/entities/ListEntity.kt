package org.purchases.best.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.purchases.best.utils.EMPTY
import org.purchases.best.utils.ZERO

@Entity(tableName = "lists")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "list_id")
    val listId: Long = Long.ZERO,

    @field:ColumnInfo(name = "list_name")
    val listName: String = String.EMPTY,
)
