package ru.bscmsk.renttable.dataDataBase.storage.models.City

import androidx.room.*

@Dao
interface CityDao {
    @Query("SELECT * FROM CityModel")
    fun get(): CityModel

    @Insert
    fun insert(genre: CityModel)

    @Delete
    fun delete(genre: CityModel)

    @Update
    fun update(genre: CityModel);
}