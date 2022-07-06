package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.UserStorage
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class DBTokensRepositoryImpl(private val userStorage: UserStorage) :
    DBTokensRepository {
    override fun dbGetAccessToken(context: Context):String{
        return userStorage.getAccessToken()
    }

    override fun dbGetRefreshToken(context: Context):String{
        return userStorage.getRefreshToken()
    }

    override fun saveTokensDB(context: Context, tokens: TokensModel){
        userStorage.saveTokensToDB(tokens)
    }

    override fun refreshTokensDB(context: Context, tokens: TokensModel){
        userStorage.refAccessToken(AccessTokenModel(1,tokens.accessToken))
        userStorage.refRefreshToken(RefreshTokenModel(1,tokens.refreshToken))
    }
}