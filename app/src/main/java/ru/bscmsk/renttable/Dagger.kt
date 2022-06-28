package ru.bscmsk.renttable

import android.content.Context
import ru.bscmsk.domain.usecase.GetCityListUseCase
import ru.bscmsk.domain.usecase.GoToOfficeFragmentUseCase
import ru.bscmsk.domain.usecase.SaveCityDBUseCase
import ru.bscmsk.renttable.fragments.CityList.CityListFragment
import ru.bscmsk.renttable.fragments.CityList.CityListViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.contracts.contract

@Component(modules = [SaveUseCaseModule::class,
        GetUseCaseModule::class,GoUseCaseModule::class, ViewFactoryModule::class, MainActivityModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: CityListFragment)
}

@Module
class SaveUseCaseModule {
    @Provides
    fun provideSaveCityDBUseCase(): SaveCityDBUseCase {
        return SaveCityDBUseCase()
    }
}

@Module
class GetUseCaseModule {
    @Provides
    fun provideGetCityListUseCase(): GetCityListUseCase {
        return GetCityListUseCase()
    }
}

@Module
class GoUseCaseModule {
    @Provides
    fun provideGoToOfficeFragmentUseCase(): GoToOfficeFragmentUseCase {
        return GoToOfficeFragmentUseCase()
    }
}

@Module
class ViewFactoryModule() {

    @Provides
    fun provideCityListViewModelFactory(
        saveCityDBUseCase: SaveCityDBUseCase,
        getCityListUseCase: GetCityListUseCase,
        goToOfficeFragmentUseCase: GoToOfficeFragmentUseCase
    ): CityListViewModelFactory
    {
        return CityListViewModelFactory(
            saveCityDBUseCase = saveCityDBUseCase,
            getCityListUseCase = getCityListUseCase,
            goToOfficeFragmentUseCase = goToOfficeFragmentUseCase
        )
    }
}

@Module
class MainActivityModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}
