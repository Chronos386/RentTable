package ru.bscmsk.renttable.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import ru.bscmsk.renttable.domain.interactors.*
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel

class MainViewModel(private val userInteractor: UserInteractor,
                    private val cityInteractor: CityInteractor
): ViewModel() {

    fun login(user: UserDataModel): Boolean = runBlocking {
        userInteractor.enterAccount(user = user)
    }

    fun getCities(): List<CityDataModel>? = runBlocking {
        cityInteractor.getCitiesList()
    }
}