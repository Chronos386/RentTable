package ru.bscmsk.renttable.domain.interactors

import ru.bscmsk.renttable.app.sealed.RentInform
import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.app.toPresentation
import ru.bscmsk.renttable.domain.repository.RentRepository
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.models.BookingPresentation
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.NewBookingPresentation
import javax.inject.Inject

class RentInteractorImpl @Inject constructor(
    private val rentRepository: RentRepository
) : RentInteractor {
    override suspend fun getRent(city: CityPresentation): Returnable =
        rentRepository.getRentByCity(city.toDomain()).let {
            when (it) {
                is RentInform.RentDomainReceived -> {
                    val listOfBooking = ArrayList<BookingPresentation>()
                    it.rentList.forEach { i -> listOfBooking.add(i.toPresentation()) }
                    RentInform.RentPresentationReceived(listOfBooking)
                }
                else -> it
            }
        }

    override suspend fun getMyRent(city: CityPresentation): Returnable =
        rentRepository.getMyRentByCity(city.toDomain()).let {
            when (it) {
                is RentInform.RentDomainReceived -> {
                    val listOfBooking = ArrayList<BookingPresentation>()
                    it.rentList.forEach { i -> listOfBooking.add(i.toPresentation()) }
                    RentInform.RentPresentationReceived(listOfBooking)
                }
                else -> it
            }
        }

    override suspend fun sendNewRentList(newRent: NewBookingPresentation): Returnable =
        rentRepository.sendNewListRentByCity(newRent.toDomain())
}