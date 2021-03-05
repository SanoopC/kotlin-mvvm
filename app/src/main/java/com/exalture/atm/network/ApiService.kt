package com.exalture.atm.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://run.mocky.io/v3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface ApiService {

    @GET("7bf58dee-16cf-45a1-9f71-a5a3c55239f3")
    suspend fun getPortfolio(): List<NetworkExaltureProjects>

}


object ExaltureApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}