package com.odukabdulbasit.rocketsworld.api

import com.odukabdulbasit.rocketsworld.rocket_list.model.AllRocketsProperties
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.spacexdata.com/v3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AllRocketApiService {
    @GET("rockets")
    fun getProperties():
            Call<List<AllRocketsProperties>>
}

object AllRocketApi {
    val RETROFIT_SERVICE_ALL : AllRocketApiService by lazy {
        retrofit.create(AllRocketApiService::class.java)
    }
}
