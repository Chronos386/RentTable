package ru.bscmsk.renttable.presentation.models


data class BookingPresentation(
    val region: String,
    val user: String,
    val datesWithPlaces: List<DateWithPlace>
)