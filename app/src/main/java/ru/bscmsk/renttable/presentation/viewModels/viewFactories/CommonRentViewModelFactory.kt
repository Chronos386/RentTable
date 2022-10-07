package ru.bscmsk.renttable.presentation.viewModels.viewFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel

class CommonRentViewModelFactory(
    val cityInteractor: CityInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommonRentViewModel(
            cityInteractor = cityInteractor
        ) as T
    }
}