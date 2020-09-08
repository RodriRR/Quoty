package com.represa.quoty.data.network

import com.represa.quoty.data.model.Credentials
import com.represa.quoty.data.model.Token
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.*

private const val BASE_URL = "https://favqs.com/api/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/**
 * Access to API endpoints
 */
interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("session")
    suspend fun createSession(@Header("Authorization") authHeader: String, @Body body: Credentials): Token

    /*@Headers("Content-Type: application/json")
    @POST("users")
    suspend fun createUser(@Header("Authorization") authHeader: String, @Body body: UserRequest): UserCreateResponse

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getQuotes(@Header("Authorization") authHeader: String, @Query("filter") filter: String): ResponseQuotes

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getRandomQuote(@Header("Authorization") authHeader: String): ResponseQuotes

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getFavQuotes(@Header("Authorization") authHeader: String, @Query("filter") filter: String, @Query("type") user: String): ResponseQuotes

    @Headers("Content-Type: application/json")
    @GET("quotes/{quote_id}")
    suspend fun getQuote(@Header("Authorization") authHeader: String, @Path("quote_id") id: Int): Quote

    @Headers("Content-Type: application/json")
    @PUT("quotes/{quote_id}/fav")
    suspend fun favQuote(@Header("Authorization") authHeader: String, @Header("User-Token") userToken: String, @Path("quote_id") id: Int): Quote

    @Headers("Content-Type: application/json")
    @PUT("quotes/{quote_id}/unfav")
    suspend fun unfavQuote(@Header("Authorization") authHeader: String, @Header("User-Token") userToken: String, @Path("quote_id") id: Int): Quote
*/
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FavQsApi {
    val RETROFIT_SERVICE: ApiService by lazy { retrofit.create(ApiService::class.java) }
}