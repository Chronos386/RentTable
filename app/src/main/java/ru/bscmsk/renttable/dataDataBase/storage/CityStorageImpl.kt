package ru.bscmsk.renttable.dataDataBase.storage

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.CityModel
import ru.bscmsk.renttable.dataDataBase.storage.models.UserBD

class CityStorageImpl private constructor(context: Context) : CityStorage{
    private val dao= UserBD.getDatabase(context).cityDao()


    override fun clearCityTable() {
        dao.delete()
    }

    override fun saveCityToDB(city:String){
        dao.insert(CityModel(1,city))
    }

    override fun getCity(): CityModel {
        return dao.get()
    }

    override fun refCity(cityModel: CityModel) {
        dao.update(cityModel)
    }

    companion object {
        private var INSTANCE: CityStorageImpl? = null
        fun getRepository(context: Context): CityStorageImpl {
            if (INSTANCE != null) {
                return INSTANCE as CityStorageImpl
            } else {
                INSTANCE = CityStorageImpl(context)
                return INSTANCE as CityStorageImpl
            }
        }
    }
}