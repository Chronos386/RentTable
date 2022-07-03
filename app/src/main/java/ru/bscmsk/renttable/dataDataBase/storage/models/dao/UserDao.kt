package ru.bscmsk.renttable.dataDataBase.storage.models.dao

import androidx.room.*
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.UserModel

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