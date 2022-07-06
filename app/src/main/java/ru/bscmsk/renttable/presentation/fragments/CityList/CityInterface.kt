package ru.bscmsk.renttable.presentation.fragments.CityList

import ru.bscmsk.renttable.domain.models.CityDataModel

interface CityInterface {
    fun onClicked(city: CityDataModel)
}