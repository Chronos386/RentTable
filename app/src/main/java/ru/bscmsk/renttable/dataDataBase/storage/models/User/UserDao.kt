package ru.bscmsk.renttable.dataDataBase.storage.models.User

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel")
    fun get(): UserModel

    @Insert
    fun insert(genre: UserModel)

    @Query("DELETE FROM UserModel")
    fun delete()

    @Update
    fun update(genre: UserModel);
}