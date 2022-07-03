package ru.bscmsk.renttable.dataDataBase.storage.models.dao

import androidx.room.*
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.AccessTokenModel


@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM AccessTokenModel")
    fun get(): AccessTokenModel

    @Insert
    fun insert(genre: AccessTokenModel)

    @Query("DELETE FROM AccessTokenModel")
    fun delete()

    @Update
    fun update(genre: AccessTokenModel);
}