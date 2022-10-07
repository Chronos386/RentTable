package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.CityPresentation

class CityListViewModel(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {
    private var resultLiveMutable = MutableLiveData<ArrayList<CityPresentation>>()
    val resultLive: LiveData<ArrayList<CityPresentation>> = resultLiveMutable

    private var gotoRentLiveMutable = MutableLiveData<Boolean>()
    val gotoRentLive: LiveData<Boolean> = gotoRentLiveMutable

    private var exitAccountMutableLive = MutableLiveData<Boolean>()
    var exitAccountLive: LiveData<Boolean> = exitAccountMutableLive

    fun getCityClick(city: CityPresentation) =
        viewModelScope.launch {
            cityInteractor.saveCity(city)
            gotoRentLiveMutable.value = true
        }

    fun getCities() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> {
                        resultLiveMutable.value = it.citiesList as ArrayList<CityPresentation>

                    }
                    else -> exitAccount()
                }
            }
        }

    private fun exitAccount() =
        viewModelScope.launch {
            userInteractor.exitAccount()
            exitAccountMutableLive.value = true
        }
}





