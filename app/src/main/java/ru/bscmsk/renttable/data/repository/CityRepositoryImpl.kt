package ru.bscmsk.renttable.data.repository

import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.app.toAccess
import ru.bscmsk.renttable.app.toData
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.app.toRefresh
import ru.bscmsk.renttable.data.storage.localDB.DataBaseStorage
import ru.bscmsk.renttable.data.storage.network.NetworkStorage
import ru.bscmsk.renttable.domain.models.CityDomain
import ru.bscmsk.renttable.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val networkStorage: NetworkStorage,
    private val dataBaseStorage: DataBaseStorage
) : CityRepository {
    override suspend fun getCitiesList(): Returnable {
        val listOfCityName = ArrayList<CityDomain>()
        networkStorage.getCityList(dataBaseStorage.getAccessToken()).let { list ->
            when (list) {
                is CitiesList.ListReceived -> {
                    list.citiesList.forEach { i -> listOfCityName.add(i.toDomain()) }
                    return CitiesList.ListDomainReceived(listOfCityName)
                }
                else -> {
                    networkStorage.checkRefreshToken(dataBaseStorage.getRefreshToken()).let {
                        return when (it) {
                            is Returnable.NewTokens -> {
                                dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                networkStorage.getCityList(dataBaseStorage.getAccessToken())
                                    .let { it1 ->
                                        when (it1) {
                                            is CitiesList.ListReceived -> {
                                                it1.citiesList.forEach { i -> listOfCityName.add(i.toDomain()) }
                                                CitiesList.ListDomainReceived(listOfCityName)
                                            }
                                            else -> it1
                                        }
                                    }
                            }
                            else -> it
                        }
                    }
                }
            }
        }
    }

    override suspend fun saveCity(city: CityDomain) {
        if (dataBaseStorage.getCity().name == "")
            dataBaseStorage.saveCity(city.toData())
        else
            dataBaseStorage.refreshCity(city.toData())
    }

    override suspend fun getCity(): CityDomain =
        dataBaseStorage.getCity().toDomain()

    override suspend fun updateCity(city: CityDomain) =
        dataBaseStorage.refreshCity(city.toData())
}