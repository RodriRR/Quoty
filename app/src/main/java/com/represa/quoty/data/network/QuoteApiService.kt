package com.represa.quoty.data.network

import com.represa.quoty.data.model.network.quote.*
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
interface QuoteApiService {

    @Headers("Content-Type: application/json")
    @POST("session")
    suspend fun createSession(@Header("Authorization") authHeader: String, @Body body: User): Token

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getQuotes(@Header("Authorization") authHeader: String, @Query("filter") filter: String): ResponseQuotes

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getFavouriteQuotes(@Header("Authorization") authHeader: String, @Query("filter") filter: String, @Query("type") user: String): ResponseQuotes

    @Headers("Content-Type: application/json")
    @POST("quotes")
    suspend fun createQuote(@Header("Authorization") authHeader: String, @Header("User-Token") sessionToken: String, @Body body: NewQuote): QuoteNetwork

    @Headers("Content-Type: application/json")
    @PUT("quotes/{quote_id}/fav")
    suspend fun favQuote(@Header("Authorization") authHeader: String, @Header("User-Token") userToken: String, @Path("quote_id") id: Int): QuoteNetwork

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
object QuoteApi {
    val QUOTE_SERVICE: QuoteApiService by lazy { retrofit.create(QuoteApiService::class.java) }
}