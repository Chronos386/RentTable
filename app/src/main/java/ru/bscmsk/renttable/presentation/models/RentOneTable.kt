package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

data class RentOneTable(
    val date: LocalDate,
    var login: String
)