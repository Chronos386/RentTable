package ru.bscmsk.renttable.data.storage.localDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccessTokenModel(
    @PrimaryKey
    var id: Int,
    var token: String
)
