package ru.bscmsk.renttable.presentation.fragments.CityList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.useCase.GetAccessTokenUseCase
import ru.bscmsk.renttable.domain.useCase.GetCityListUseCase
import ru.bscmsk.renttable.domain.useCase.GoToOfficeFragmentUseCase
import ru.bscmsk.renttable.domain.useCase.SaveCityUseCase

class CityListViewModelFactory(
    val cityInteractor: CityInteractor,
    val saveCityUseCase: SaveCityUseCase,
    val goToOfficeFragmentUseCase: GoToOfficeFragmentUseCase,
): ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return CityListViewModel(
            cityInteractor = cityInteractor,
            saveCityUseCase = saveCityUseCase,
            goToOfficeFragmentUseCase = goToOfficeFragmentUseCase,
        ) as T
    }
}