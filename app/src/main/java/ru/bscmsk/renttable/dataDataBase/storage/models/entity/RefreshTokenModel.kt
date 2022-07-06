package ru.bscmsk.renttable.dataDataBase.storage.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RefreshTokenModel(
    @PrimaryKey
    var id:Int,
    val token:String
)
