package ru.bscmsk.renttable.presentation.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.MainViewModel

class BaseViewModelFactory(
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        BaseViewModel() as T
}