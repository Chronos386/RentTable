package ru.bscmsk.renttable.data.storage.localDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RefreshTokenModel(
    @PrimaryKey
    var id:Int,
    val token:String
)
