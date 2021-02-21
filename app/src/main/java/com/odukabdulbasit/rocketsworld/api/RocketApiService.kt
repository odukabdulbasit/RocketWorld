package com.odukabdulbasit.rocketsworld.api

import com.odukabdulbasit.rocketsworld.rocket_detail.OneRocketProperties
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL_OneRocket = "https://api.spacexdata.com/v3/rockets/"

private val onemoshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(onemoshi))
        .baseUrl(BASE_URL_OneRocket)
        .build()


interface RocketApiService {
    @GET("falcon9")
    fun getProperties():
            Call<List<OneRocketProperties>>
}

object RocketApi {
    val RETROFIT_SERVICE : RocketApiService by lazy {
        retrofit.create(RocketApiService::class.java)
    }
}
