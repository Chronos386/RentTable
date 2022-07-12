package ru.bscmsk.renttable.data.storage.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bscmsk.renttable.data.storage.localDB.dao.AccessTokenDao
import ru.bscmsk.renttable.data.storage.localDB.entity.AccessTokenModel
import ru.bscmsk.renttable.data.storage.localDB.dao.CityDao
import ru.bscmsk.renttable.data.storage.localDB.entity.CityModel
import ru.bscmsk.renttable.data.storage.localDB.dao.RefreshTokenDao
import ru.bscmsk.renttable.data.storage.localDB.entity.RefreshTokenModel
import ru.bscmsk.renttable.data.storage.localDB.dao.UserDao
import ru.bscmsk.renttable.data.storage.localDB.entity.UserModel


@Database(
    entities = [UserModel::class, CityModel::class, RefreshTokenModel::class,
        AccessTokenModel::class], version = 1
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun accessTokenDao(): AccessTokenDao
    abstract fun refreshTokenDao(): RefreshTokenDao
    abstract fun cityDao(): CityDao
    abstract fun userDao(): UserDao
}