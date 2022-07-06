package ru.bscmsk.renttable.dataDataBase.storage

import ru.bscmsk.renttable.dataDataBase.storage.models.entity.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.UserModel


interface UserStorage {

    fun clearAccessTokenTable()

    fun clearRefreshTokenTable()

    fun clearUserTable()

    fun saveTokensToDB(tokens: TokensModel)

    fun saveUserToDB(user:String)

    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun getUser(): UserModel

    fun refAccessToken(accessToken: AccessTokenModel)

    fun refRefreshToken(refreshToken: RefreshTokenModel)

    fun refUser(userModel: UserModel)
}