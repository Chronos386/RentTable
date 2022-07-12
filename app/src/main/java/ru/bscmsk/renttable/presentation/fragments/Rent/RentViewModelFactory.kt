package ru.bscmsk.renttable.presentation.fragments.Rent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.fragments.CityList.CityListViewModel

class RentViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return RentViewModel(
        ) as T
    }
}