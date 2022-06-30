package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorage
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class DBTokensRepositoryImpl(private val dbStorage: DBStorage) :
    DBTokensRepository {
    override fun dbGetAccessToken(context: Context):String{
        dbStorage.initDataBase(context)
        return dbStorage.getAccessToken()
    }

    override fun dbGetRefreshToken(context: Context):String{
        dbStorage.initDataBase(context)
        return dbStorage.getRefreshToken()
    }

    override fun saveTokensDB(context: Context, tokens: TokensModel){
        dbStorage.initDataBase(context)
        dbStorage.saveTokensToDB(tokens)
    }

    override fun refreshTokensDB(context: Context, tokens: TokensModel){
        dbStorage.initDataBase(context)
        dbStorage.refAccessToken(AccessTokenModel(1,tokens.accessToken))
        dbStorage.refRefreshToken(RefreshTokenModel(1,tokens.refreshToken))
    }
}