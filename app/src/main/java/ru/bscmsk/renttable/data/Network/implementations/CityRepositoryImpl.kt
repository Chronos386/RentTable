package ru.bscmsk.renttable.data.Network.implementations

import ru.bscmsk.renttable.data.Network.storage.NetworkStorage
import ru.bscmsk.renttable.data.Network.storage.models.AccessTokenModel
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