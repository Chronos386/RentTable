package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.domain.models.CityDomain

interface CityRepository {
    suspend fun getCitiesList(): Returnable
    suspend fun getCityInform(): Returnable
    suspend fun saveCity(city: CityDomain)
    suspend fun getCity(): CityDomain
    suspend fun updateCity(city: CityDomain)
}