package ru.bscmsk.renttable.dataNetwork.storage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bscmsk.renttable.dataNetwork.storage.models.*

class NetworkStorageImpl: NetworkStorage {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://25.54.65.89:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkRequests::class.java)

    override fun enterToAccount(user: UserModel): TokensModel {
        val result = retrofit.getTokens(user.login, user.password).execute()
        return if (result.code() == 200)
            TokensModel(result.body()!!.accessToken, result.body()!!.refreshToken)
        else
            TokensModel("", "")
    }

    override fun getCityList(token: AccessTokenModel): CityListModel {
        val accessToken = token.token
        val result = retrofit.getCityList("Bearer $accessToken").execute()
        return result.body()?.let { CityListModel(it) }!!
    }

    override fun checkAccessToken(accessToken: AccessTokenModel): Boolean {
        val token = accessToken.token
        val result = retrofit.getCityList("Bearer $token").execute()
        return result.code() == 200
    }

    override fun checkRefreshToken(refreshToken: RefreshTokenModel): TokensModel {
        val token = refreshToken.token
        val result = retrofit.getNewTokens("Bearer $token").execute()
        return if (result.code() == 200)
            TokensModel(result.body()!!.accessToken, result.body()!!.refreshToken)
        else
            TokensModel("", "")
    }
}