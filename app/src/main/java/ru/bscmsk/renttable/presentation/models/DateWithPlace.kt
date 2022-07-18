package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

data class DateWithPlace (
    val date: LocalDate,
    val place: Int
)