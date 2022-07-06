package ru.bscmsk.renttable.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.interactors.UserInteractor
import ru.bscmsk.renttable.domain.useCase.CheckRefreshTokenUseCase
import ru.bscmsk.renttable.domain.useCase.GetCityListUseCase
import ru.bscmsk.renttable.domain.useCase.SendUserDataUseCase

class MainViewModelFactory(private val userInteractor: UserInteractor,
                           private val cityInteractor: CityInteractor
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(userInteractor, cityInteractor) as T
}