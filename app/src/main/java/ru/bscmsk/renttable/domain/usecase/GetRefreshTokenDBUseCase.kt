package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class GetRefreshTokenDBUseCase(private val dbTokensRepository: DBTokensRepository) {
    fun execute(context: Context):RefreshTokenDataModel {
        val refToken = RefreshTokenDataModel(dbTokensRepository.dbGetRefreshToken(context = context))
        return refToken
    }
}