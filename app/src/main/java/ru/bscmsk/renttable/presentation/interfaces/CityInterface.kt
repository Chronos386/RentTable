package ru.bscmsk.renttable.presentation.interfaces

import ru.bscmsk.renttable.presentation.models.CityPresentation

interface CityInterface {
    fun onClicked(city: CityPresentation)
}