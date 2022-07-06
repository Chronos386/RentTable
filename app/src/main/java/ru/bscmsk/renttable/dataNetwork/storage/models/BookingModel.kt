package ru.bscmsk.renttable.dataNetwork.storage.models

data class BookingModel (
    val region: CityModel,
    val user: String,
    val placeWithDate: List<BookingDetailsModel>
)