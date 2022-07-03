package ru.bscmsk.renttable.dataDataBase.storage.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.bscmsk.renttable.dataDataBase.storage.models.dao.AccessTokenDao
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.dao.CityDao
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.CityModel
import ru.bscmsk.renttable.dataDataBase.storage.models.dao.RefreshTokenDao
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.dao.UserDao
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.UserModel


@Database(entities = [UserModel::class, CityModel::class, RefreshTokenModel::class, AccessTokenModel::class], version = 1)
abstract class UserBD : RoomDatabase() {
    abstract fun accessTokenDao(): AccessTokenDao
    abstract fun refreshTokenDao(): RefreshTokenDao
    abstract fun cityDao(): CityDao
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserBD? = null

        fun getDatabase(context: Context): UserBD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserBD::class.java, "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}