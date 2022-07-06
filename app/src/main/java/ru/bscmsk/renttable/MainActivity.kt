package ru.bscmsk.renttable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.bscmsk.renttable.dataNetwork.repository.CityRepositoryImpl
import ru.bscmsk.renttable.dataNetwork.repository.RentRepositoryImpl
import ru.bscmsk.renttable.dataNetwork.repository.UserRepositoryImpl
import ru.bscmsk.renttable.dataNetwork.storage.network.NetworkStorageImpl
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.interactors.UserInteractor
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.useCase.*
import ru.bscmsk.renttable.presentation.MainViewModel
import ru.bscmsk.renttable.presentation.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val storage = NetworkStorageImpl()
    private val sendUser = SendUserDataUseCase(UserRepositoryImpl(storage))
    private val saveTokens = SaveTokensUseCase(UserRepositoryImpl(storage))
    private val getAccess = GetAccessTokenUseCase(UserRepositoryImpl(storage))
    private val getRefresh = GetRefreshTokenUseCase(UserRepositoryImpl(storage))
    private val checkRefreshToken = CheckRefreshTokenUseCase(UserRepositoryImpl(storage))
    private val refreshTokens = RefreshTokensUseCase(UserRepositoryImpl(storage))
    private val clearDB = ClearDataBaseUseCase(RentRepositoryImpl())
    private val getCityList = GetCityListUseCase(CityRepositoryImpl(storage))
    private val userInteractor = UserInteractor(sendUser, saveTokens)
    private val cityInteractor = CityInteractor(getAccess, getRefresh, checkRefreshToken,
        refreshTokens, clearDB, getCityList)
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(userInteractor, cityInteractor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = UserDataModel("LOGIN", "PASSWORD")
        println(viewModel.login(user))
        println(viewModel.getCities())
    }
}