package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.models.CityPresentation

class CommonRentViewModel(
    private val cityInteractor: CityInteractor
) : ViewModel() {
    private val newCityMutableLive = MutableLiveData<CityPresentation>()
    val newCityLive: LiveData<CityPresentation> = newCityMutableLive

    fun rewriteCity(newCity: CityPresentation) =
        viewModelScope.launch {
            cityInteractor.updateCity(newCity)
            newCityMutableLive.value = newCity
        }
}