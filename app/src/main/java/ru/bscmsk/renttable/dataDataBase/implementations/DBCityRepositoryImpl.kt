package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.repository.DBCityRepository

class DBCityRepositoryImpl (private val dbStorageImpl: DBStorageImpl):
    DBCityRepository{
    override fun saveCity(context: Context,City: CityDataModel){
        dbStorageImpl.initDataBase(context)
        dbStorageImpl.saveCityToDB(City.name)
    }

    override fun check(context: Context):Boolean{
        dbStorageImpl.initDataBase(context)
        if (dbStorageImpl.getCity() == null)
        {
            return true
        }
        return false
    }

}