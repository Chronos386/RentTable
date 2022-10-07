package ru.bscmsk.renttable.data.storage.models

import java.time.LocalDate

data class ClearBooking(
    val region: String,
    val places: List<Int>,
    val dates: List<LocalDate>
)