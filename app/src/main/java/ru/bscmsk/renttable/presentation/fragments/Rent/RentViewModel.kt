package ru.bscmsk.renttable.presentation.fragments.Rent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bscmsk.renttable.domain.models.CityDataModel

class RentViewModel: ViewModel() {

    private val CityListLiveMutable = MutableLiveData<List<CityDataModel>>() //Тип можно заменить
    val CityListLive: LiveData<List<CityDataModel>> = CityListLiveMutable

    private val CityLiveMutable = MutableLiveData<String>() //Тип можно заменить
    val CityLive: LiveData<String> = CityLiveMutable

}