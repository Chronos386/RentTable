package ru.bscmsk.renttable.app.sealed

import ru.bscmsk.renttable.data.storage.models.TokensData

sealed class Returnable {
    object OldTokens : Returnable()
    class NewTokens(val tokens: TokensData) : Returnable()
}