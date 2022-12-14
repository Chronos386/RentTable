package ru.bscmsk.renttable.presentation.interactors

import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.presentation.models.CityPresentation

interface CityInteractor {
    suspend fun getCitiesList(): Returnable
    suspend fun saveCity(city: CityPresentation)
    suspend fun getCityInform(): Returnable
    suspend fun getCity(): CityPresentation
    suspend fun checkCity(): Boolean
    suspend fun updateCity(city: CityPresentation)
}