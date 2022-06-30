package ru.bscmsk.renttable.domain.repository

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel

interface DBTokensRepository {
    fun dbGetAccessToken(context: Context):String

    fun dbGetRefreshToken(context: Context):String

    fun saveTokensDB(context: Context, tokens: TokensModel)

    fun refreshTokensDB(context: Context, tokens: TokensModel)
}