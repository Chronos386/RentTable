package ru.bscmsk.renttable.domain.models

import java.time.LocalDate

data class BookingDomain (
    val region: String,
    val user: String,
    val places: List<Int>,
    val dates: List<LocalDate>
)