package ru.bscmsk.renttable.data.Network.storage

import retrofit2.Response
import retrofit2.http.*
import ru.bscmsk.renttable.data.Network.storage.models.BookingModel
import ru.bscmsk.renttable.data.Network.storage.models.CityModel
import ru.bscmsk.renttable.data.Network.storage.models.TokensModel

interface NetworkRequests {
    @POST("auth/login")
    suspend fun getTokens(
        @Query("login") login: String,
        @Query("password") password: String
    ): Response<TokensModel>

    @GET("region")
    suspend fun getCityList(
        @Header("Authorization") token: String
    ): Response<List<CityModel>>

    @GET("auth/refresh")
    suspend fun getNewTokens(
        @Header("Authorization") refToken: String
    ): Response<TokensModel>

    @GET("rent/{city}")
    suspend fun getRentTables(
        @Path("city") city: String,
        @Header("Authorization") token: String
    ): Response<List<BookingModel>>

    @GET("rent/{city}/self")
    suspend fun getMyRentTables(
        @Path("city") city: String,
        @Header("Authorization") token: String
    ): Response<List<BookingModel>>
}