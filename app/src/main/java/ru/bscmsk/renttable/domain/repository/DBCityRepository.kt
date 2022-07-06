package ru.bscmsk.renttable.domain.repository

import android.content.Context
import ru.bscmsk.renttable.domain.models.CityDataModel

interface DBCityRepository {
    fun saveCity(context: Context, City: CityDataModel)

    fun check(context: Context):Boolean
}