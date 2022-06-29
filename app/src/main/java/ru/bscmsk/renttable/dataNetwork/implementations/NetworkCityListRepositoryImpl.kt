package ru.bscmsk.renttable.dataNetwork.implementations

import ru.bscmsk.renttable.dataNetwork.storage.NetworkStorage
import ru.bscmsk.renttable.dataNetwork.storage.models.AccessTokenModel
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.models.CityListDataModel
import ru.bscmsk.renttable.domain.repository.NetworkCityListRepository

class NetworkCityListRepositoryImpl(private val networkStorage: NetworkStorage):
    NetworkCityListRepository {

    override fun getCitiesList(token: AccessTokenDataModel): CityListDataModel {
        val newToken = AccessTokenModel(token.token)
        val listOfCities = networkStorage.getCityList(newToken)
        val listOfCityName = ArrayList<CityDataModel>()
        for(item in listOfCities.list){
            listOfCityName.add(CityDataModel(item.name))
        }
        return CityListDataModel(listOfCityName)
    }
}