/*package ru.bscmsk.renttable.presentation.dagger

import dagger.Module
import dagger.Provides
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.useCase.GetAccessTokenUseCase
import ru.bscmsk.renttable.domain.useCase.GetCityListUseCase
import ru.bscmsk.renttable.domain.useCase.GoToOfficeFragmentUseCase
import ru.bscmsk.renttable.domain.useCase.SaveCityUseCase
import ru.bscmsk.renttable.fragments.CityList.CityListViewModelFactory

@Module
class AppModule
{
    @Provides
    fun provideCityListViewModelFactory(
        saveCityUseCase: SaveCityUseCase,
        goToOfficeFragmentUseCase: GoToOfficeFragmentUseCase,
        cityInteractor: CityInteractor
    ): CityListViewModelFactory
    {
        return CityListViewModelFactory(
            saveCityUseCase = saveCityUseCase,
            goToOfficeFragmentUseCase = goToOfficeFragmentUseCase,
            cityInteractor = cityInteractor

        )
    }
}*/