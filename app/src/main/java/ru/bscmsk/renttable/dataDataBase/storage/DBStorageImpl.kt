package ru.bscmsk.renttable.dataDataBase.storage

import android.content.Context
import androidx.room.Room
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.City.CityModel
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.dataDataBase.storage.models.User.UserModel
import ru.bscmsk.renttable.dataDataBase.storage.models.UserBD


class DBStorageImpl : DBStorage{
    private lateinit var myData: UserBD

    override fun initDataBase(cont: Context){
        myData = Room.databaseBuilder(
            cont,
            UserBD::class.java,
            "myDataBase"
        ).build()
    }

    override fun clearUserDB() {
        myData.accessTokenDao().delete(myData.accessTokenDao().get())
        myData.refreshTokenDao().delete(myData.refreshTokenDao().get())
        myData.cityDao().delete(myData.cityDao().get())
        myData.userDao().delete(myData.userDao().get())
    }

    override fun clearAccessTokenTable() {
        myData.accessTokenDao().delete(myData.accessTokenDao().get())
    }

    override fun clearRefreshTokenTable() {
        myData.refreshTokenDao().delete(myData.refreshTokenDao().get())
    }

    override fun clearCityTable() {
        myData.cityDao().delete(myData.cityDao().get())
    }

    override fun clearUserTable() {
        myData.userDao().delete(myData.userDao().get())
    }

    override fun saveTokensToDB(tokens: TokensModel){
        myData.accessTokenDao().insert(AccessTokenModel(1,tokens.accessToken))
        myData.refreshTokenDao().insert(RefreshTokenModel(1,tokens.refreshToken))
    }

    override fun saveCityToDB(city:String){
        myData.cityDao().insert(CityModel(1,city))
    }

    override fun saveUserToDB(user:String){
        myData.userDao().insert(UserModel(1,user))
    }

    override fun getAccessToken(): String {
        return myData.accessTokenDao().get().token
    }

    override fun getRefreshToken(): String {
        return myData.refreshTokenDao().get().token
    }

    override fun getCity(): CityModel {
        return myData.cityDao().get()
    }

    override fun getUser(): UserModel {
        return myData.userDao().get()
    }

    override fun refAccessToken(accessToken:AccessTokenModel) {
        return myData.accessTokenDao().update(accessToken)
    }

    override fun refRefreshToken(refreshToken:RefreshTokenModel) {
        myData.refreshTokenDao().update(refreshToken)
    }

    override fun refCity(cityModel: CityModel) {
        myData.cityDao().update(cityModel)
    }

    override fun refUser(userModel: UserModel) {
        return myData.userDao().update(userModel)
    }
}