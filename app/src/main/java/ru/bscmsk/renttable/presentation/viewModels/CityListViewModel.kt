package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.models.CityPresentation

class CityListViewModel(
    private val cityInteractor: CityInteractor
) : ViewModel() {
    private var resultLiveMutable = MutableLiveData<ArrayList<CityPresentation>>()
    val resultLive: LiveData<ArrayList<CityPresentation>> = resultLiveMutable

    fun getCityClick(city: CityPresentation) =
        viewModelScope.launch {
            cityInteractor.saveCity(city)
            println("Переход на 3-е окно")
        }

    fun getCities() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> resultLiveMutable.value =
                        it.citiesList as ArrayList<CityPresentation>
                    else -> println("Переход на окно логина")
                }
            }
        }
}





