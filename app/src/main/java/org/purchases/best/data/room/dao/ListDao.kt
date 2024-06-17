package org.purchases.best.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.purchases.best.data.room.entities.ListEntity

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveList(listEntity: ListEntity): Long

    @Query("DELETE FROM lists WHERE list_id = :listId")
    suspend fun deleteList(listId: Long)

    @Query("SELECT * FROM lists")
    suspend fun getLists(): List<ListEntity>?

}