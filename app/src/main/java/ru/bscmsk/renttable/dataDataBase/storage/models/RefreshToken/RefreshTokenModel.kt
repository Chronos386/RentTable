package ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RefreshTokenModel(
    @PrimaryKey
    var id:Int,
    val token:String
)
