package ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken

import androidx.room.*

@Dao
interface RefreshTokenDao {
    @Query("SELECT * FROM RefreshTokenModel")
    fun get(): RefreshTokenModel

    @Insert
    fun insert(genre: RefreshTokenModel)

    @Query("DELETE FROM RefreshTokenModel")
    fun delete()

    @Update
    fun update(genre: RefreshTokenModel);
}