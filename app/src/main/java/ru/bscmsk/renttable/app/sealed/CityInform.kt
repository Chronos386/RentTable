package ru.bscmsk.renttable.app.sealed

import ru.bscmsk.renttable.data.storage.models.CityInformData
import ru.bscmsk.renttable.domain.models.CityInformDomain
import ru.bscmsk.renttable.presentation.models.CityInformPresentation

sealed class CityInform : Returnable() {
    class InformReceived(val cityInformData: CityInformData) : CityInform()
    class InformDomainReceived(val cityInformDomain: CityInformDomain) : CityInform()
    class InformPresentationReceived(val cityInformPresentation: CityInformPresentation) :
        CityInform()
}