package ru.bscmsk.renttable.dataDataBase.storage

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.AccessTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.RefreshTokenModel
import ru.bscmsk.renttable.dataDataBase.storage.models.TokensModel
import ru.bscmsk.renttable.dataDataBase.storage.models.entity.UserModel
import ru.bscmsk.renttable.dataDataBase.storage.models.UserBD


class UserStorageImpl private constructor(context: Context) : UserStorage{
    private val myData = UserBD.getDatabase(context)

    override fun clearAccessTokenTable() {
        myData.accessTokenDao().delete()
    }

    override fun clearRefreshTokenTable() {
        myData.refreshTokenDao().delete()
    }

    override fun clearUserTable() {
        myData.userDao().delete()
    }

    override fun saveTokensToDB(tokens: TokensModel){
        myData.accessTokenDao().insert(AccessTokenModel(1,tokens.accessToken))
        myData.refreshTokenDao().insert(RefreshTokenModel(1,tokens.refreshToken))
    }

    override fun saveUserToDB(user:String){
        myData.userDao().insert(UserModel(1,user))
    }

    override fun getAccessToken(): String {
        return myData.accessTokenDao().get().token
    }

    override fun getRefreshToken(): String {
        return myData.refreshTokenDao().get().token
    }

    override fun getUser(): UserModel {
        return myData.userDao().get()
    }

    override fun refAccessToken(accessToken: AccessTokenModel) {
        return myData.accessTokenDao().update(accessToken)
    }

    override fun refRefreshToken(refreshToken: RefreshTokenModel) {
        myData.refreshTokenDao().update(refreshToken)
    }

    override fun refUser(userModel: UserModel) {
        return myData.userDao().update(userModel)
    }

    companion object {
        private var INSTANCE: UserStorageImpl? = null
        fun getRepository(context: Context): UserStorageImpl {
            if (INSTANCE != null) {
                return INSTANCE as UserStorageImpl
            } else {
                INSTANCE = UserStorageImpl(context)
                return INSTANCE as UserStorageImpl
            }
        }
    }
}