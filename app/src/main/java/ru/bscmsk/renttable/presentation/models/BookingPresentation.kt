package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

data class BookingPresentation (
    val region: String,
    val user: String,
    val places: List<Int>,
    val dates: List<LocalDate>
)