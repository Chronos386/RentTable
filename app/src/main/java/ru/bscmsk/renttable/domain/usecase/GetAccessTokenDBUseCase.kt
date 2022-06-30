package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class GetAccessTokenDBUseCase(private val dbTokensRepository: DBTokensRepository) {
    fun execute(context: Context):AccessTokenDataModel {
        val accToken = AccessTokenDataModel(dbTokensRepository.dbGetAccessToken(context = context))
        return accToken
    }
}