package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.CityListDataModel

interface NetworkCityListRepository {
    fun getCitiesList(token: AccessTokenDataModel): CityListDataModel
}