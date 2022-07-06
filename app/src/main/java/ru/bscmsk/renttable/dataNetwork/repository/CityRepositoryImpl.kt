package ru.bscmsk.renttable.dataNetwork.repository

import ru.bscmsk.renttable.dataNetwork.storage.network.NetworkStorage
import ru.bscmsk.renttable.dataNetwork.storage.models.AccessTokenModel
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.repository.CityRepository

class CityRepositoryImpl(private val networkStorage: NetworkStorage):
    CityRepository {

    override suspend fun getCitiesList(token: AccessTokenDataModel): List<CityDataModel>? {
        val listOfCityName = ArrayList<CityDataModel>()
        AccessTokenModel(token.token).let { newToken ->
            networkStorage.getCityList(newToken)?.run {
                forEach {
                    listOfCityName.add(CityDataModel(it.name))
                }
            }
        }
        return when(listOfCityName.size) {
            0 -> null
            else -> listOfCityName
        }
    }
}