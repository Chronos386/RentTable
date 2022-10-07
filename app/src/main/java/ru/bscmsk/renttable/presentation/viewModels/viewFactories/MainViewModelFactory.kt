package ru.bscmsk.renttable.presentation.viewModels.viewFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.viewModels.MainViewModel

class MainViewModelFactory(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            cityInteractor = cityInteractor,
            userInteractor = userInteractor
        ) as T
    }
}