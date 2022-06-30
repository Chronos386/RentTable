package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.repository.DBCityRepository

class SaveCityDBUseCase(private val dbCityRepository: DBCityRepository) {
    fun execute(context: Context, cityDataModel: CityDataModel) {
        dbCityRepository.saveCity(context=context,City=cityDataModel)
    }
}