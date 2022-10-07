package ru.bscmsk.renttable.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.app.sealed.CityInform
import ru.bscmsk.renttable.app.sealed.DataPosted
import ru.bscmsk.renttable.app.sealed.RentInform
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.app.translateMonthToString
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class RentViewModel(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor,
    private val rentInteractor: RentInteractor
) : ViewModel() {
    private val cityListMutableLive = MutableLiveData<List<CityPresentation>>()
    val cityListLive: LiveData<List<CityPresentation>> = cityListMutableLive

    private var currentTableIndexMutableLive = MutableLiveData<Int>()
    var currentTableIndexLive: LiveData<Int> = currentTableIndexMutableLive

    private var userCityListMutableLive = MutableLiveData<List<CityPresentation>>()
    var userCityListLive: LiveData<List<CityPresentation>> = userCityListMutableLive

    private var userDataListMutableLive = MutableLiveData<List<DateWithPlace>>()
    var userDataListLive: LiveData<List<DateWithPlace>> = userDataListMutableLive

    private var rentOneTableListMutableLive = MutableLiveData<List<RentOneTable>>()
    var rentOneTableListLive: LiveData<List<RentOneTable>> = rentOneTableListMutableLive


    private var exitAccountMutableLive = MutableLiveData<Boolean>()
    var exitAccountLive: LiveData<Boolean> = exitAccountMutableLive

    private val freeTablesListMutableLive = MutableLiveData<List<RentFewTables>>()
    val freeTablesListLive: LiveData<List<RentFewTables>> = freeTablesListMutableLive

    private val dataRangeMutableLive = MutableLiveData<List<String>>()
    val dataRangeLive: LiveData<List<String>> = dataRangeMutableLive

    private var saveSuccessMutableLive = MutableLiveData<Boolean>()
    var saveSuccessLive: LiveData<Boolean> = saveSuccessMutableLive

    private var deleteSuccessMutableLive = MutableLiveData<Boolean>()
    var deleteSuccessLive: LiveData<Boolean> = deleteSuccessMutableLive

    private var cityInformMutableLive = MutableLiveData<CityInformPresentation>()
    var cityInformLive: LiveData<CityInformPresentation> = cityInformMutableLive

    fun getCityFromDB(): CityPresentation = runBlocking {
        CityPresentation(cityInteractor.getCity().name)
    }

    fun getCityList() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> cityListMutableLive.value =
                        it.citiesList
                    else -> exitAccount()
                }
            }
        }

    fun getUser(): UserPresentation = runBlocking {
        userInteractor.getUser()
    }

    fun getUserCityList() =
        viewModelScope.launch {
            cityInteractor.getCitiesList().let {
                when (it) {
                    is CitiesList.ListPresentationReceived -> {
                        val userCityList: ArrayList<CityPresentation> = ArrayList()
                        it.citiesList.forEach { city ->
                            rentInteractor.getMyRent(city).let { myRent ->
                                when (myRent) {
                                    is RentInform.RentPresentationReceived -> {
                                        if (myRent.rentList.isNotEmpty()) {
                                            userCityList.add(city)
                                        }
                                    }
                                    else -> exitAccountMutableLive.value = true
                                }
                            }
                        }
                        userCityListMutableLive.value = userCityList
                    }
                    else -> exitAccount()
                }
            }
        }

    fun getUserData(city: CityPresentation) =
        runBlocking {
            rentInteractor.getMyRent(city).let {
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        val result = ArrayList<DateWithPlace>()
                        if (it.rentList.isNotEmpty()) {
                            it.rentList[0].datesWithPlaces.forEach { it1 ->
                                if (it1.date >= LocalDate.now() && it1.date <= LocalDate.now()
                                        .plusDays(30)
                                ) {
                                    result.add(it1)
                                }
                            }
                        }
                        userDataListMutableLive.value = result
                    }
                    else -> exitAccount()
                }
            }
        }

    fun getTables(): List<TablePresentation> {
        var tables: ArrayList<TablePresentation> = arrayListOf()
        getCityInform()
        cityInformMutableLive.value.let {
            if (it != null) {
                tables = it.places as ArrayList<TablePresentation>
            }
        }
        return tables
    }

    fun getCityInform() = runBlocking {
        cityInteractor.getCityInform().let {
            when (it) {
                is CityInform.InformPresentationReceived -> {
                    cityInformMutableLive.value = it.cityInformPresentation
                }
                else -> exitAccount()
            }
        }
    }

    fun getDaysWithAllTables(firstDate: String, endDate: String) =
        viewModelScope.launch {
            val city = getCityFromDB()
            rentInteractor.getRent(city).let {
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        println("lol" + it.rentList)
                        val result: ArrayList<RentFewTables> = ArrayList()
                        var fDate = LocalDate.parse(
                            firstDate.replace('/', '.').replace('-', '.'),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        )
                        var eDate = LocalDate.parse(
                            endDate.replace('/', '.').replace('-', '.'),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        )
                        if (fDate > eDate) {
                            freeTablesListMutableLive.value = result
                        } else {
                            if (fDate < LocalDate.now())
                                fDate = LocalDate.now()
                            if (eDate > LocalDate.now().plusDays(30))
                                eDate = LocalDate.now().plusDays(30)
                            var date = fDate
                            val tables = getTables()
                            while (date <= eDate) {
                                result.add(RentFewTables(date, tables, 0))
                                date = date.plusDays(1)
                            }
                            val lUser = getUser()
                            it.rentList.forEach { rentList ->
                                rentList.datesWithPlaces.forEach { datesWithPlaces ->
                                    if (datesWithPlaces.date >= fDate && datesWithPlaces.date <= eDate) {
                                        val tab =
                                            result[result.indexOf(result.filter { it1 -> it1.date == datesWithPlaces.date }[0])].tableList.toMutableList()
                                        if (rentList.user == lUser.login)
                                            tab.clear()
                                        else {
                                            if (tab.isNotEmpty())
                                                tab.removeAt(tab.indexOf(tab.filter { it1 -> it1.number == datesWithPlaces.place }[0]))
                                        }
                                        result[result.indexOf(result.filter { it1 -> it1.date == datesWithPlaces.date }[0])].tableList =
                                            tab
                                    }
                                }
                            }
                            val del: ArrayList<RentFewTables> = ArrayList()
                            result.forEach { it1 ->
                                if (it1.tableList.isEmpty())
                                    del.add(it1)
                            }
                            del.forEach { it1 ->
                                result.remove(it1)
                            }
                            freeTablesListMutableLive.value = result
                        }
                    }
                    else -> exitAccount()
                }
            }
        }


    fun changeTableIndex(newIndex: Int) {
        currentTableIndexMutableLive.value = newIndex
    }

    fun getDaysListWithOneTable(city: CityPresentation, table: TablePresentation, month: Month) =
        runBlocking {
            rentInteractor.getRent(city).let {
                when (it) {
                    is RentInform.RentPresentationReceived -> {
                        val dates: ArrayList<RentOneTable> = ArrayList()
                        var date = LocalDate.now()
                        while (date <= LocalDate.now().plusDays(30)) {
                            dates.add(RentOneTable(date, String()))
                            date = date.plusDays(1)
                        }
                        val result =
                            dates.filter { data -> translateMonthToString(data.date.month.value) == month.name } as ArrayList<RentOneTable>
                        it.rentList.forEach { rentList ->
                            rentList.datesWithPlaces.filter { date -> translateMonthToString(date.date.month.value) == month.name }
                                .forEach { datesWithPlaces ->
                                    if (datesWithPlaces.place == table.number && datesWithPlaces.date >= LocalDate.now())
                                        result[result.indexOf(
                                            RentOneTable(
                                                datesWithPlaces.date,
                                                String()
                                            )
                                        )] = RentOneTable(datesWithPlaces.date, rentList.user)
                                }
                        }
                        rentOneTableListMutableLive.value = result
                    }
                    else -> exitAccount()
                }
            }
        }

    fun rentListTables(newRent: NewBookingPresentation) =
        runBlocking {
            Log.e("AAA", "Сохранено")
            rentInteractor.sendNewRent(newRent).let {
                when (it) {
                    is DataPosted.IsPosted -> {
                        saveSuccessMutableLive.value = true
                    }
                    is DataPosted.NotPosted -> {
                        saveSuccessMutableLive.value = false
                    }
                    else -> exitAccount()
                }
            }
        }

    fun exitAccount() =
        viewModelScope.launch {
            userInteractor.exitAccount()
            exitAccountMutableLive.value = true
        }

    fun saveData(DataStart: String, DataEnd: String) {
        dataRangeMutableLive.value = listOf(DataStart, DataEnd)
    }


    fun changeDataIndex(date: LocalDate, currentIndex: Int) {
        freeTablesListMutableLive.value.let { it1 ->
            it1?.get(it1.indexOf(it1.filter { it.date == date }[0]))?.currentIndex = currentIndex
        }
    }

    fun deleteRent(rent: NewBookingPresentation) =
        runBlocking {
            Log.e("AAA", "Удалено")
            rentInteractor.deleteRent(rent).let {
                when (it) {
                    is DataPosted.IsPosted -> {
                        deleteSuccessMutableLive.value = true
                    }
                    is DataPosted.NotPosted -> {
                        deleteSuccessMutableLive.value = false
                    }
                    else -> exitAccount()
                }
            }
        }


    fun deleteAllRent(city: CityPresentation) =
        runBlocking {
            rentInteractor.clearMyRent(city).let {
                when (it) {
                    is DataPosted.IsPosted -> {
                        deleteSuccessMutableLive.value = true
                    }
                    is DataPosted.NotPosted -> {
                        deleteSuccessMutableLive.value = false
                    }
                    else -> exitAccount()
                }
            }
        }
}