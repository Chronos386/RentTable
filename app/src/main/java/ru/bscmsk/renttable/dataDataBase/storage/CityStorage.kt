package ru.bscmsk.renttable.dataDataBase.storage

import ru.bscmsk.renttable.dataDataBase.storage.models.entity.CityModel


interface CityStorage {

    fun clearCityTable()

    fun saveCityToDB(city:String)

    fun getCity(): CityModel

    fun refCity(cityModel: CityModel)
}