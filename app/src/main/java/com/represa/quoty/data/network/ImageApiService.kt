package com.represa.quoty.data.network

import com.represa.quoty.data.model.network.images.Image
import com.represa.quoty.data.model.network.images.ImageResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.*

private const val BASE_URL = "https://api.unsplash.com/"

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
interface ImageApiService {

    @Headers("Content-Type: application/json")
    @GET("photos/random")
    suspend fun getRandomPhoto(@Header("Authorization") authHeader: String, @Query("query") query: String, @Query("count") count: Int): List<Image>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImageApi {
    val IMAGE_SERVICE: ImageApiService by lazy { retrofit.create(ImageApiService::class.java) }
}