package ru.bscmsk.renttable.dataDataBase.storage.models.City

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityModel(
    @PrimaryKey
    var id:Int,
    val name: String
)
