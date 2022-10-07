package ru.bscmsk.renttable.data.storage.models

data class BookingData(
    val region: String,
    val user: String,
    val dateWithPlace: Map<String, String>
)