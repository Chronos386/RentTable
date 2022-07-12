package ru.bscmsk.renttable.presentation.fragments.Rent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bscmsk.renttable.domain.models.CityDomain

class RentViewModel: ViewModel() {

    private val CityListLiveMutable = MutableLiveData<List<String>>() //Тип можно заменить
    val CityListLive: LiveData<List<String>> = CityListLiveMutable

    private val CityLiveMutable = MutableLiveData<String>() //Тип можно заменить
    val CityLive: LiveData<String> = CityLiveMutable

}