package ru.bscmsk.renttable.dataDataBase.storage.models.dao

import androidx.room.*
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.RefreshTokenModel

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