package ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken

import androidx.room.*


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