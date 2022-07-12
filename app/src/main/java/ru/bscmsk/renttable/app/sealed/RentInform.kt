package ru.bscmsk.renttable.app.sealed

import ru.bscmsk.renttable.data.storage.models.BookingData
import ru.bscmsk.renttable.domain.models.BookingDomain
import ru.bscmsk.renttable.presentation.models.BookingPresentation

sealed class RentInform : Returnable() {
    class RentReceived(val rentList: List<BookingData>) : RentInform()
    class RentDomainReceived(val rentList: List<BookingDomain>) : RentInform()
    class RentPresentationReceived(val rentList: List<BookingPresentation>) : RentInform()
}