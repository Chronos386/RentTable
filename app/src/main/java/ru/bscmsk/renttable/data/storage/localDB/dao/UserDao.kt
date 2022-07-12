package ru.bscmsk.renttable.data.storage.localDB.dao

import androidx.room.*
import ru.bscmsk.renttable.data.storage.localDB.entity.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel")
    suspend fun get(): UserModel?

    @Insert
    suspend fun insert(user: UserModel)

    @Query("DELETE FROM UserModel")
    suspend fun delete()
}