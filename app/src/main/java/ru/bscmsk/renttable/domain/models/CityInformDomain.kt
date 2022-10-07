package ru.bscmsk.renttable.domain.models

data class CityInformDomain(
    val imageUrl: String,
    val places: List<TableDomain>
)