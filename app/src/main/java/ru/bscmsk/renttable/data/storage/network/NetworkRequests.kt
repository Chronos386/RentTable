package ru.bscmsk.renttable.data.storage.network

import retrofit2.Response
import retrofit2.http.*
import ru.bscmsk.renttable.data.storage.models.*

interface NetworkRequests {
    @POST("auth/login")
    suspend fun getTokens(
        @Query("login") login: String,
        @Query("password") password: String
    ): Response<TokensData>

    @GET("region")
    suspend fun getCityList(
        @Header("Authorization") token: String
    ): Response<List<CityData>>

    @GET("region/{city}")
    suspend fun getCityInform(
        @Path("city") city: String,
        @Header("Authorization") token: String
    ): Response<CityInformData>

    @GET("auth/refresh")
    suspend fun getNewTokens(
        @Header("Authorization") refToken: String
    ): Response<TokensData>

    @GET("rent/{city}")
    suspend fun getRentTables(
        @Path("city") city: String,
        @Header("Authorization") token: String
    ): Response<List<BookingData>>

    @GET("rent/{city}/self")
    suspend fun getMyRentTables(
        @Path("city") city: String,
        @Header("Authorization") token: String
    ): Response<List<BookingData>>

    @POST("rent")
    suspend fun sendNewBooking(
        @Body newBooking: NewBookingData,
        @Header("Authorization") token: String
    ): Response<Void>

    @PUT("rent")
    suspend fun clearMyBooking(
        @Body newBooking: NewBookingData,
        @Header("Authorization") token: String
    ): Response<Void>

    @HTTP(method = "DELETE", path = "rent", hasBody = true)
    suspend fun deleteBooking(
        @Body booking: NewBookingData,
        @Header("Authorization") token: String
    ): Response<Void>

    companion object {
        const val BASE_URL = "http://25.54.65.89:8080/api/v1/"
    }
}