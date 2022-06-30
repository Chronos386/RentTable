package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.repository.DBTokensRepository

class RefreshTokensDBUseCase(private val dbTokensRepository: DBTokensRepository) {
    fun execute(context: Context, tokensDataModel: TokensDataModel) {
        dbTokensRepository.refreshTokensDB(context=context,
            tokens=TokensModel(tokensDataModel.accessToken,tokensDataModel.refreshToken))
    }
}