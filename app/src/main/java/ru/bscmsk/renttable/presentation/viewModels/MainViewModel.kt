package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor

class MainViewModel(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {
    private val userCheckMutableLive = MutableLiveData<Boolean>()
    val userCheckLive: LiveData<Boolean> = userCheckMutableLive

    private val cityCheckMutableLive = MutableLiveData<Boolean>()
    val cityCheckLive: LiveData<Boolean> = cityCheckMutableLive

    fun checkUser() = viewModelScope.launch {
        userCheckMutableLive.value = userInteractor.checkUser()
    }

    fun checkCity() = viewModelScope.launch {
        cityCheckMutableLive.value = cityInteractor.checkCity()
    }
}