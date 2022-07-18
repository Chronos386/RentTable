package ru.bscmsk.renttable.presentation.interfaces

import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.NewBookingPresentation

interface RentInterface {
    fun onClicked(datalist:NewBookingPresentation)
}