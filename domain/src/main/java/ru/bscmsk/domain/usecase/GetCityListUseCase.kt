package ru.bscmsk.domain.usecase

import ru.bscmsk.domain.models.CityDataModel
import ru.bscmsk.domain.models.CityListDataModel

class GetCityListUseCase {
    fun execute(): CityListDataModel {
        return CityListDataModel(cities = ArrayList<CityDataModel>())
    }
}