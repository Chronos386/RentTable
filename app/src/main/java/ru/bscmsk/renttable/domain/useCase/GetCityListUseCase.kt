package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.*
import ru.bscmsk.renttable.domain.repository.CityRepository

class GetCityListUseCase(private val networkCityListRepo: CityRepository) {
    suspend fun execute(token: AccessTokenDataModel): List<CityDataModel>? {
        return networkCityListRepo.getCitiesList(token = token)
    }
}