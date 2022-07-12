package ru.bscmsk.renttable.domain.models

import java.time.LocalDate

data class NewBookingDomain (
    val region: String,
    val places: List<Int>,
    val dates: List<LocalDate>
)