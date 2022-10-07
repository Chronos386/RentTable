package ru.bscmsk.renttable.presentation.interactors

import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.NewBookingPresentation

interface RentInteractor {
    suspend fun getRent(city: CityPresentation): Returnable
    suspend fun getMyRent(city: CityPresentation): Returnable
    suspend fun sendNewRent(newRent: NewBookingPresentation): Returnable
    suspend fun clearMyRent(city: CityPresentation): Returnable
    suspend fun deleteRent(rent: NewBookingPresentation): Returnable
}