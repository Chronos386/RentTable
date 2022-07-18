package ru.bscmsk.renttable.presentation.fragments.Rent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.app.sealed.DataPosted
import ru.bscmsk.renttable.app.sealed.RentInform
import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.domain.models.CityDomain
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.presentation.translateMonthtoInt
import ru.bscmsk.renttable.presentation.translateMonthtoString
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class RentViewModel(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor,
    private val rentInteractor: RentInteractor): ViewModel() {

    private val CityListMutableLive = MutableLiveData<List<CityPresentation>>()
    val CityListLive: LiveData<List<CityPresentation>> = CityListMutableLive

    private var CurrentTableIndexMutableLive = MutableLiveData<Int>()
    var CurrentTableIndexLive: LiveData<Int> = CurrentTableIndexMutableLive

    private var UserCityListMutableLive = MutableLiveData<List<CityPresentation>>()
    var UserCityListLive: LiveData<List<CityPresentation>> = UserCityListMutableLive

    private var UserDataListMutableLive = MutableLiveData<List<DateWithPlace>>()
    var UserDataListLive: LiveData<List<DateWithPlace>> = UserDataListMutableLive

    private var RentOneTableListMutableLive = MutableLiveData<List<RentOneTable>>()
    var RentOneTableListLive: LiveData<List<RentOneTable>> = RentOneTableListMutableLive


    private var ExitAccountMutableLive = MutableLiveData<Boolean>()
    var ExitAccountLive: LiveData<Boolean> = ExitAccountMutableLive

    private val FreeTablesListMutableLive = MutableLiveData<List<RentFewTables>>()
    val FreeTablesListLive: LiveData<List<RentFewTables>> = FreeTablesListMutableLive

    private val DataDiapozonMutableLive = MutableLiveData<List<String>>()
    val DataDiapozonLive: LiveData<List<String>> = DataDiapozonMutableLive


    private var saveSuccessMutableLive = MutableLiveData<Boolean>() //Тип можно заменить
    var saveSuccessLive: LiveData<Boolean> = saveSuccessMutableLive


    fun getCityFromDB():LiveData<CityPresentation>{
        var result = MutableLiveData<CityPresentation>()
        viewModelScope.launch {
            result.value = cityInteractor.getCity()
        }
        return result
    }

    fun getCityList() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> CityListMutableLive.value =
                        it.citiesList
                    else -> ExitAccount()
                }
            }
        }

    fun getUser():LiveData<UserPresentation>{

        val UsernameMutableLive = MutableLiveData<UserPresentation>()
        viewModelScope.launch {
            UsernameMutableLive.value = userInteractor.getUser()
        }
        return UsernameMutableLive
    }


    fun getUserCityList() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> {
                        var UserCityList: ArrayList<CityPresentation> = ArrayList<CityPresentation>()

                        it.citiesList.forEach {city->
                            rentInteractor.getMyRent(city).let{
                                when (it) {
                                    is RentInform.RentPresentationReceived ->
                                    {
                                        if (it.rentList.size !=0)
                                        {
                                            UserCityList.add(CityPresentation(it.rentList[0].region))
                                        }
                                    }
                                    else -> ExitAccountMutableLive.value = true
                                }
                            }
                        }
                        UserCityListMutableLive.value = UserCityList
                    }
                    else -> ExitAccount()
                }
            }
        }

    fun getUserData(city: CityPresentation) =
        viewModelScope.launch {
            rentInteractor.getMyRent(city).let{
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        UserDataListMutableLive.value = it.rentList[0].datesWithPlaces
                    }
                    else -> ExitAccount()
                }
            }
        }


    fun getTables(city:CityPresentation):List<Table>{
        val tables = ArrayList<Table>()
        for (i in 1..20)
            tables.add(Table(i))
        return tables.toList()
    }

    fun getDaysWithAllTables(city:CityPresentation,firstDate:String,endDate:String)=
        viewModelScope.launch {
            rentInteractor.getRent(city).let{
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        val result : ArrayList<RentFewTables> = ArrayList()
                        var fDate = LocalDate.parse(firstDate.replace('/', '.').replace('-', '.'),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        var eDate = LocalDate.parse(endDate.replace('/', '.').replace('-', '.'),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        if(fDate>eDate){
                            FreeTablesListMutableLive.value = result
                        }
                        else{
                            if (fDate < LocalDate.now())
                                fDate = LocalDate.now()
                            if (eDate > LocalDate.now().plusDays(30))
                                eDate = LocalDate.now().plusDays(30)
                            var date = fDate
                            val tables = getTables(city)
                            while(date<=eDate) {
                                result.add(RentFewTables(date,tables,0))
                                date = date.plusDays(1)
                            }

                            it.rentList.forEach { rentList ->
                                rentList.datesWithPlaces.forEach { datesWithPlaces ->
                                    if(datesWithPlaces.date>=fDate && datesWithPlaces.date<=eDate)
                                    {
                                        val tab = result[result.indexOf(result.filter { it.Date==datesWithPlaces.date}[0])].Tablelist.toMutableList()
                                        tab.removeAt(tab.indexOf(tab.filter { it.number==datesWithPlaces.place }[0]))
                                        result[result.indexOf(result.filter { it.Date==datesWithPlaces.date}[0])].Tablelist = tab
                                    }
                                }
                            }
                            result.forEach {
                                if(it.Tablelist.isEmpty())
                                    result.remove(it)
                            }
                            FreeTablesListMutableLive.value = result
                        }
                    }
                    else -> ExitAccount()
                }
            }
        }

    fun changeIndex(newindex: Int){
        CurrentTableIndexMutableLive.value = newindex
    }

    fun getDaysListWithOneTable(city:CityPresentation,table: Table,month: Month)=
        viewModelScope.launch {
            rentInteractor.getRent(city).let{
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        val dates : ArrayList<RentOneTable> = ArrayList()
                        var date = LocalDate.now()
                        while (date<=LocalDate.now().plusDays(30))
                        {
                            dates.add(RentOneTable(date, String()))
                            date = date.plusDays(1)
                        }
                        val result = dates.filter {data -> translateMonthtoString(data.Date.month.value)==month.name } as ArrayList<RentOneTable>
                        it.rentList.forEach { rentList ->
                            rentList.datesWithPlaces.filter{translateMonthtoString(it.date.month.value) == month.name}
                                .forEach { datesWithPlaces ->
                                    if (datesWithPlaces.place == table.number)
                                        result[result.indexOf(RentOneTable(datesWithPlaces.date,String()))]= RentOneTable(datesWithPlaces.date,rentList.user)
                            }
                        }
                        RentOneTableListMutableLive.value = result
                    }
                    else -> ExitAccount()
                }
            }
        }

    fun rentListTables(newRent : NewBookingPresentation)=
        viewModelScope.launch {
            rentInteractor.sendNewRentList(newRent).let {
                when (it) {
                    is DataPosted.IsPosted -> {
                        saveSuccessMutableLive.value = true
                    }
                    is DataPosted.NotPosted -> {
                        saveSuccessMutableLive.value = false
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


    fun saveData(DataStart:String, DataEnd:String){
        DataDiapozonMutableLive.value = listOf(DataStart,DataEnd)
    }

}