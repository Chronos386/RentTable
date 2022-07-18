package ru.bscmsk.renttable.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.CityPresentation

class MainViewModel(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor
):ViewModel()
{
    private val UserCheckMutableLive = MutableLiveData<Boolean>()
    val UserCheckLive: LiveData<Boolean> = UserCheckMutableLive

    private val CityCheckMutableLive = MutableLiveData<Boolean>()
    val CityCheckLive: LiveData<Boolean> = CityCheckMutableLive


    fun checkUser()=
        viewModelScope.launch {
            UserCheckMutableLive.value = userInteractor.checkUser()

        }

    fun checkCity()=
        viewModelScope.launch {
            CityCheckMutableLive.value = cityInteractor.checkCity()
        }


}