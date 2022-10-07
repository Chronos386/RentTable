package ru.bscmsk.renttable.presentation.viewModels.viewFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.viewModels.CityListViewModel

class CityListViewModelFactory(
    val cityInteractor: CityInteractor,
    val userInteractor: UserInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CityListViewModel(
            cityInteractor = cityInteractor,
            userInteractor = userInteractor
        ) as T
    }
}