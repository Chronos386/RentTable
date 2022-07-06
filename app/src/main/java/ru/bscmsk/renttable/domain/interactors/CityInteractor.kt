package ru.bscmsk.renttable.domain.interactors

import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.useCase.GetCityListUseCase
import ru.bscmsk.renttable.domain.useCase.*

class CityInteractor (
    private val getAccess: GetAccessTokenUseCase,
    private val getRefresh: GetRefreshTokenUseCase,
    private val checkRefreshToken: CheckRefreshTokenUseCase,
    private val refreshTokens: RefreshTokensUseCase,
    private val clearDB: ClearDataBaseUseCase,
    private val getCityList: GetCityListUseCase
    ) {
    suspend fun getCitiesList(): List<CityDataModel>? {
        getCityList.execute(getAccess.execute()).let {
            when (it) {
                null -> checkRefreshToken.execute(getRefresh.execute()).let { tokens ->
                    when (tokens) {
                        null -> {
                            clearDB.execute()
                            return tokens
                        }
                        else -> {
                            refreshTokens.execute(tokens)
                            return getCityList.execute(getAccess.execute())
                        }
                    }
                }
                else -> return it
            }
        }
    }
}