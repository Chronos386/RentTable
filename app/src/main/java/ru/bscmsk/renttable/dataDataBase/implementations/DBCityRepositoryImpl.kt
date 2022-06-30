package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorage
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.repository.DBCityRepository

class DBCityRepositoryImpl (private val dbStorage: DBStorage):
    DBCityRepository{
    override fun saveCity(context: Context,City: CityDataModel){
        dbStorage.initDataBase(context)
        dbStorage.saveCityToDB(City.name)
    }

    override fun check(context: Context):Boolean{
        dbStorage.initDataBase(context)
        if (dbStorage.getCity() == null)
        {
            return true
        }
        return false
    }

}