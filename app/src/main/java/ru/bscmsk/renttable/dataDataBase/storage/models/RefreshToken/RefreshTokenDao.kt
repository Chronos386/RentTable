package ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken

import androidx.room.*

@Dao
interface RefreshTokenDao {
    @Query("SELECT * FROM RefreshTokenModel")
    fun get(): RefreshTokenModel

    @Insert
    fun insert(genre: RefreshTokenModel)

    @Delete
    fun delete(genre: RefreshTokenModel)

    @Update
    fun update(genre: RefreshTokenModel);
}