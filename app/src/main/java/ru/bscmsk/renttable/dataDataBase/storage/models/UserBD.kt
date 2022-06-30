package ru.bscmsk.renttable.dataDataBase.storage.models

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenDao
import ru.bscmsk.renttable.dataDataBase.storage.models.AccessToken.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.City.CityDao
import ru.bscmsk.renttable.dataDataBase.storage.models.City.CityModel
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenDao
import ru.bscmsk.renttable.dataDataBase.storage.models.RefreshToken.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.User.UserDao
import ru.bscmsk.renttable.dataDataBase.storage.models.User.UserModel


@Database(entities = [UserModel::class, CityModel::class, RefreshTokenModel::class, AccessTokenModel::class], version = 1)
abstract class UserBD : RoomDatabase() {
    abstract fun accessTokenDao():AccessTokenDao
    abstract fun refreshTokenDao(): RefreshTokenDao
    abstract fun cityDao(): CityDao
    abstract fun userDao(): UserDao
}