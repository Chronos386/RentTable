package ru.bscmsk.renttable.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.interactors.UserInteractor
import ru.bscmsk.renttable.domain.models.CityDataModel

class BaseViewModel(
): ViewModel() {
    private val errorLiveMutable = MutableLiveData<List<CityDataModel>>()
    val errorLive: LiveData<List<CityDataModel>> = errorLiveMutable
}