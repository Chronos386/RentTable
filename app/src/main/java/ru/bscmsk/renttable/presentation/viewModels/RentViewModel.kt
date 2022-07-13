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

    private var CurrentTableIndexMutableLive = MutableLiveData<Int>() //Тип можно заменить
    var CurrentTableIndexLive: LiveData<Int> = CurrentTableIndexMutableLive


    private var saveSuccessMutableLive = MutableLiveData<Boolean>() //Тип можно заменить
    var saveSuccessLive: LiveData<Boolean> = saveSuccessMutableLive


    private var ListofDataWithTablesMutableLive = MutableLiveData<List<String>>() //Тип можно заменить
    var ListofDataWithTablesLive: LiveData<List<String>> = ListofDataWithTablesMutableLive


    private var IndexListMutableLive = MutableLiveData<List<Int>>() //Тип можно заменить
    var IndexListLive: LiveData<List<Int>> = IndexListMutableLive
}