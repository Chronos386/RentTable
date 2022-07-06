package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.CityDataModel

interface CityRepository {
    suspend fun getCitiesList(token: AccessTokenDataModel): List<CityDataModel>?
}