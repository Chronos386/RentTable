package ru.bscmsk.renttable.dataDataBase.storage.models.City

import androidx.room.*

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