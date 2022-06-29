package ru.bscmsk.renttable.dataNetwork.storage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.bscmsk.renttable.dataNetwork.storage.models.CityModel
import ru.bscmsk.renttable.dataNetwork.storage.models.TokensModel

interface NetworkRequests {
    @GET("auth/login")
    fun getTokens(@Query("login") login: String, @Query("password") password: String):
            Call<TokensModel>
    @GET("region")
    fun getCityList(@Header("Authorization") token: String): Call<List<CityModel>>
    @GET("auth/refresh")
    fun getNewTokens(@Header("Authorization") refToken: String): Call<TokensModel>
}