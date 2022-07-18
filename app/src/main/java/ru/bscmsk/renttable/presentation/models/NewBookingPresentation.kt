package ru.bscmsk.renttable.presentation.models


data class NewBookingPresentation(
    val region: String,
    val datesWithPlaces: List<DateWithPlace>
)