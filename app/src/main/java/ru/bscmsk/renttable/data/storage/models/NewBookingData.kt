package ru.bscmsk.renttable.data.storage.models

data class NewBookingData(
    val region: String,
    val dateWithPlace: Map<String, String>
)