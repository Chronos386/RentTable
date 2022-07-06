package ru.bscmsk.renttable.domain.models

data class TokensDataModel(
    val accessToken: String,
    val refreshToken: String
)