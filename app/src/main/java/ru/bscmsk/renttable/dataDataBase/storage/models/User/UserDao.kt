package ru.bscmsk.renttable.dataDataBase.storage.models.User

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel")
    fun get(): UserModel

    @Insert
    fun insert(genre: UserModel)

    @Delete
    fun delete(genre: UserModel)

    @Update
    fun update(genre: UserModel);
}