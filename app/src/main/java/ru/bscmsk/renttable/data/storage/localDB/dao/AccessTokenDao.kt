package ru.bscmsk.renttable.data.storage.localDB.dao

import androidx.room.*
import ru.bscmsk.renttable.data.storage.localDB.entity.AccessTokenModel

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM AccessTokenModel")
    suspend fun get(): AccessTokenModel

    @Insert
    suspend fun insert(genre: AccessTokenModel)

    @Query("DELETE FROM AccessTokenModel")
    suspend fun delete()

    @Update
    suspend fun update(genre: AccessTokenModel)
}