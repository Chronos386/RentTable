package ru.bscmsk.renttable.dataDataBase.storage

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.City.CityModel
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.dataDataBase.storage.models.User.UserModel


interface DBStorage {
    fun initDataBase(cont: Context)

    fun clearUserDB()

    fun clearAccessTokenTable()

    fun clearRefreshTokenTable()

    fun clearCityTable()

    fun clearUserTable()

    fun saveTokensToDB(tokens: TokensModel)

    fun saveCityToDB(city:String)

    fun saveUserToDB(user:String)

    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun getCity(): CityModel

    fun getUser(): UserModel

    fun refAccessToken(accessToken: AccessTokenModel)

    fun refRefreshToken(refreshToken: RefreshTokenModel)

    fun refCity(cityModel: CityModel)

    fun refUser(userModel: UserModel)
}