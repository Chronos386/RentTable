package ru.bscmsk.renttable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bscmsk.renttable.dataDataBase.implementations.DBCityRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBTablesRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBTokensRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBUserRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.storage.CityStorage
import ru.bscmsk.renttable.dataDataBase.storage.CityStorageImpl
import ru.bscmsk.renttable.dataDataBase.storage.UserStorage
import ru.bscmsk.renttable.dataDataBase.storage.UserStorageImpl
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.usecase.*

class MainActivity : AppCompatActivity() {
    private val userStorage : UserStorage by lazy{UserStorageImpl.getRepository(this)}
    private val cityStorage : CityStorage by lazy{ CityStorageImpl.getRepository(this)}
    private val firstDBUseCase: CheckNullCityDBUseCase by lazy{ CheckNullCityDBUseCase(DBCityRepositoryImpl(cityStorage))}
    private val secondDBUseCase :SaveCityDBUseCase by lazy{SaveCityDBUseCase(DBCityRepositoryImpl(cityStorage))}
    private val thirdDBUseCase :GetAccessTokenDBUseCase by lazy{GetAccessTokenDBUseCase(DBTokensRepositoryImpl(userStorage))}
    private val fourtDBUseCase :GetRefreshTokenDBUseCase by lazy{GetRefreshTokenDBUseCase(DBTokensRepositoryImpl(userStorage))}
    private val fifthDBUseCase :SaveTokensDBUseCase by lazy{SaveTokensDBUseCase(DBTokensRepositoryImpl(userStorage))}
    private val sixthDBUseCase :RefreshTokensDBUseCase by lazy{RefreshTokensDBUseCase(DBTokensRepositoryImpl(userStorage))}
    private val seventhDBUseCase :ClearDBUseCase by lazy{ClearDBUseCase(DBTablesRepositoryImpl(userStorage,cityStorage))}
    private val eighthDBUseCase :SaveUserDBUseCase by lazy{SaveUserDBUseCase(DBUserRepositoryImpl(userStorage))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(userStorage)
        println(UserStorageImpl.getRepository(this))
        val thr = Thread(kotlinx.coroutines.Runnable {
            seventhDBUseCase.execute()
            eighthDBUseCase.execute(this, userDataModel = UserDataModel("w","3"))
            println(firstDBUseCase.execute(this))
            fifthDBUseCase.execute(this, tokensDataModel = TokensDataModel("1","2"))
            println(thirdDBUseCase.execute(this).token)
            println(fourtDBUseCase.execute(this).token)
            sixthDBUseCase.execute(this, tokensDataModel = TokensDataModel("3","4"))
            println(thirdDBUseCase.execute(this).token)
            println(fourtDBUseCase.execute(this).token)
            secondDBUseCase.execute(this, CityDataModel("Белградище"))
            println(firstDBUseCase.execute(this))
        })
        thr.start()

    }
}