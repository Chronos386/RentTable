package ru.bscmsk.renttable.data.storage.localDB.dao

import androidx.room.*
import ru.bscmsk.renttable.data.storage.localDB.entity.CityModel

@Dao
interface CityDao {
    @Query("SELECT * FROM CityModel")
    suspend fun get(): CityModel?

    @Insert
    suspend fun insert(genre: CityModel)

    @Query("DELETE FROM CityModel")
    suspend fun delete()

    @Update
    suspend fun update(genre: CityModel)
}