package ru.bscmsk.renttable.dataDataBase.storage.models.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey
    var id:Int,
    var login:String
)
