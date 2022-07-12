package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

data class NewBookingPresentation (
    val region: String,
    val places: List<Int>,
    val dates: List<LocalDate>
)