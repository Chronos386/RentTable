package ru.bscmsk.renttable.data.storage.localDB.dao

import androidx.room.*
import ru.bscmsk.renttable.data.storage.localDB.entity.RefreshTokenModel

@Dao
interface RefreshTokenDao {
    @Query("SELECT * FROM RefreshTokenModel")
    suspend fun get(): RefreshTokenModel?

    @Insert
    suspend fun insert(genre: RefreshTokenModel)

    @Query("DELETE FROM RefreshTokenModel")
    suspend fun delete()

    @Update
    suspend fun update(genre: RefreshTokenModel)
}