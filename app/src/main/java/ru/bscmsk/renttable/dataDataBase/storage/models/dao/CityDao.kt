package ru.bscmsk.renttable.dataDataBase.storage.models.dao

import androidx.room.*
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.CityModel

@Dao
interface CityDao {
    @Query("SELECT * FROM CityModel")
    fun get(): CityModel

    @Insert
    fun insert(genre: CityModel)

    @Query("DELETE FROM CityModel")
    fun delete()

    @Update
    fun update(genre: CityModel);
}