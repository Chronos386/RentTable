package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.CityListDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.NetworkCityListRepository
import ru.bscmsk.renttable.domain.repository.NetworkLoginRepository

class GetCityListUseCase(private val networkCityListRepo: NetworkCityListRepository) {
    suspend fun execute(token: AccessTokenDataModel): CityListDataModel {
        return networkCityListRepo.getCitiesList(token = token)
    }
}