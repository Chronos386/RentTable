package ru.bscmsk.renttable.presentation.viewModels.viewFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel

class RentViewModelFactory(
    private val cityInteractor: CityInteractor,
    private val userInteractor: UserInteractor,
    private val rentInteractor: RentInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RentViewModel(
            cityInteractor = cityInteractor,
            userInteractor = userInteractor,
            rentInteractor = rentInteractor
        ) as T
    }
}
