package ru.bscmsk.renttable.domain.interactors

import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.useCase.SaveTokensUseCase
import ru.bscmsk.renttable.domain.useCase.SendUserDataUseCase

class UserInteractor(
    private val sendUser: SendUserDataUseCase,
    private val saveTokens: SaveTokensUseCase
    ) {
    suspend fun enterAccount(user: UserDataModel): Boolean {
        sendUser.execute(user).let {
            when (it) {
                null -> return false
                else -> {
                    saveTokens.execute(it)
                    return true
                }
            }
        }
    }
}