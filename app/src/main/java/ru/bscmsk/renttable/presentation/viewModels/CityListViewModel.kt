package ru.bscmsk.renttable.presentation.viewModels

import android.util.Log
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

    private var ExitAccountMutableLive = MutableLiveData<Boolean>()
    var ExitAccountLive: LiveData<Boolean> = ExitAccountMutableLive

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
                    else -> ExitAccount()
                }
            }
        }

    fun ExitAccount() =
        viewModelScope.launch {
            userInteractor.exitAccount()
            ExitAccountMutableLive.value = true
        }
}





