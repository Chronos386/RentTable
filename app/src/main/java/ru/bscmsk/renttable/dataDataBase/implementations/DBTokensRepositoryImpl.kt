package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class DBTokensRepositoryImpl(private val dbStorageImpl: DBStorageImpl) :
    DBTokensRepository {
    override fun dbGetAccessToken(context: Context):String{
        dbStorageImpl.initDataBase(context)
        return dbStorageImpl.getAccessToken()
    }

    override fun dbGetRefreshToken(context: Context):String{
        dbStorageImpl.initDataBase(context)
        return dbStorageImpl.getRefreshToken()
    }

    override fun saveTokensDB(context: Context, tokens: TokensModel){
        dbStorageImpl.initDataBase(context)
        dbStorageImpl.saveTokensToDB(tokens)
    }

    override fun refreshTokensDB(context: Context, tokens: TokensModel){
        dbStorageImpl.initDataBase(context)
        dbStorageImpl.refAccessToken(AccessTokenModel(1,tokens.accessToken))
        dbStorageImpl.refRefreshToken(RefreshTokenModel(1,tokens.refreshToken))
    }
}