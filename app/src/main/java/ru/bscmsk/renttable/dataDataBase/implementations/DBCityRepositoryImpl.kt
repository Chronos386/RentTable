package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.CityStorage
import ru.bscmsk.renttable.dataDataBase.storage.UserStorage
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.repository.DBCityRepository

class DBCityRepositoryImpl (private val cityStorage: CityStorage):
    DBCityRepository{
    override fun saveCity(context: Context,City: CityDataModel){
        cityStorage.saveCityToDB(City.name)
    }

    override fun check(context: Context):Boolean{
        cityStorage.getCity().let{result ->
            return when(result){
                null -> true
                else -> false
            }
        }
    }

}