package ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccessTokenModel(
    @PrimaryKey
    var id:Int,
    var token:String
)
