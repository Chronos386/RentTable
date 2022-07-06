/*package ru.bscmsk.renttable.presentation.dagger

import dagger.Module
import dagger.Provides
import ru.bscmsk.renttable.domain.repository.NetworkCityListRepository
import ru.bscmsk.renttable.domain.repository.NetworkLoginRepository
import ru.bscmsk.renttable.domain.repository.NetworkTokenRepository
import ru.bscmsk.renttable.domain.useCase.*

@Module
class DomainModule {


    @Provides
    fun provideCheckAccessTokenUseCase(networkTokenRepo: NetworkTokenRepository): CheckAccessTokenUseCase
    {
        return CheckAccessTokenUseCase(networkTokenRepo = networkTokenRepo)
    }

    @Provides
    fun provideCheckRefreshTokenUseCase(networkTokenRepo: NetworkTokenRepository): CheckRefreshTokenUseCase
    {
        return CheckRefreshTokenUseCase(networkTokenRepo = networkTokenRepo)
    }

    @Provides
    fun provideGetAccessTokenUseCase(): GetAccessTokenUseCase {
        return GetAccessTokenUseCase()
    }

    @Provides
    fun provideGetCityListUseCase(networkCityListRepo: NetworkCityListRepository): GetCityListUseCase {
        return GetCityListUseCase(networkCityListRepo = networkCityListRepo)
    }

    @Provides
    fun provideGoToOfficeFragmentUseCase(): GoToOfficeFragmentUseCase {
        return GoToOfficeFragmentUseCase()
    }

    @Provides
    fun provideSaveCityDBUseCase(): SaveCityDBUseCase {
        return SaveCityDBUseCase()
    }

    @Provides
    fun provideSendUserDataUsecase(networkLoginRepo: NetworkLoginRepository):
            SendUserDataUseCase
    {
        return SendUserDataUseCase(networkLoginRepo = networkLoginRepo)
    }
}*/