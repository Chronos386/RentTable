package ru.bscmsk.renttable.app.sealed

sealed class UserAuthorized {
    object IsAuthorized : UserAuthorized()

    object NotAuthorized : UserAuthorized()
}