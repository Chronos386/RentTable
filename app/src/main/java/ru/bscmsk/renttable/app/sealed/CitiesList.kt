package ru.bscmsk.renttable.app.sealed

import ru.bscmsk.renttable.data.storage.models.CityData
import ru.bscmsk.renttable.domain.models.CityDomain
import ru.bscmsk.renttable.presentation.models.CityPresentation

sealed class CitiesList : Returnable() {
    class ListReceived(val citiesList: List<CityData>) : CitiesList()
    class ListDomainReceived(val citiesList: List<CityDomain>) : CitiesList()
    class ListPresentationReceived(val citiesList: List<CityPresentation>) : CitiesList()
}