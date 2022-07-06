package ru.bscmsk.renttable.data.Network.storage.models

data class BookingModel (
    val region: CityModel,
    val user: String,
    val placeWithDate: List<BookingDetailsModel>
)