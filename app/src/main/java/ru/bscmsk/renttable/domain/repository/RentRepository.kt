package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.domain.models.CityDomain
import ru.bscmsk.renttable.domain.models.NewBookingDomain

interface RentRepository {
    suspend fun getRentByCity(city: CityDomain): Returnable
    suspend fun getMyRentByCity(city: CityDomain): Returnable
    suspend fun clearMyRent(city: CityDomain): Returnable
    suspend fun deleteRent(booking: NewBookingDomain): Returnable
    suspend fun sendNewRentByCity(newBooking: NewBookingDomain): Returnable
}