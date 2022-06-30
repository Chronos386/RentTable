package ru.bscmsk.renttable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bscmsk.renttable.dataDataBase.implementations.DBCityRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBTablesRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBTokensRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.implementations.DBUserRepositoryImpl
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.usecase.*

class MainActivity : AppCompatActivity() {
    private val storage = DBStorageImpl()
    private val firstDBUseCase = CheckNullCityDBUseCase(DBCityRepositoryImpl(storage))
    private val secondDBUseCase = SaveCityDBUseCase(DBCityRepositoryImpl(storage))
    private val thirdDBUseCase = GetAccessTokenDBUseCase(DBTokensRepositoryImpl(storage))
    private val fourtDBUseCase = GetRefreshTokenDBUseCase(DBTokensRepositoryImpl(storage))
    private val fifthDBUseCase = SaveTokensDBUseCase(DBTokensRepositoryImpl(storage))
    private val sixthDBUseCase = RefreshTokensDBUseCase(DBTokensRepositoryImpl(storage))
    private val seventhDBUseCase = ClearDBUseCase(DBTablesRepositoryImpl(storage))
    private val eighthDBUseCase = SaveUserDBUseCase(DBUserRepositoryImpl(storage))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thr = Thread(kotlinx.coroutines.Runnable {
            //seventhDBUseCase.execute(this)
            println(111111110)
            eighthDBUseCase.execute(this, userDataModel = UserDataModel("w","3"))
            println(11111111)
            println(firstDBUseCase.execute(this))
            println(22222222)
            fifthDBUseCase.execute(this, tokensDataModel = TokensDataModel("1","2"))
            println(thirdDBUseCase.execute(this).token)
            println(44444444)
            println(fourtDBUseCase.execute(this).token)
            println(33333333)
            sixthDBUseCase.execute(this, tokensDataModel = TokensDataModel("3","4"))
            println(thirdDBUseCase.execute(this).token)
            println(44444444)
            println(fourtDBUseCase.execute(this).token)
            //secondDBUseCase.execute(this, CityDataModel("Белградище"))
            println(firstDBUseCase.execute(this))
        })
        thr.start()

    }
}