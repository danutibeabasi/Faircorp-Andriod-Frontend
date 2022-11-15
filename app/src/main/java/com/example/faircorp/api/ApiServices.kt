package com.example.faircorp.api

import com.example.faircorp.model.BasicAuthInterceptor
import com.faircorp.apiservices.WindowApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiServices {

    const val API_USERNAME = "admin"
    const val API_PASSWORD = "password"

    val windowApiService : WindowApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(API_USERNAME, API_PASSWORD))
            .build()

        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .baseUrl("https://utibeabasidan.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }
    val roomApiService: RoomApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(API_USERNAME, API_PASSWORD))
            .build()

        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://utibeabasidan.cleverapps.io/api/")
            .client(client)
            .build()
            .create(RoomApiService::class.java)
    }

    val heaterApiService: HeaterApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(API_USERNAME, API_PASSWORD))
            .build()

        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .baseUrl("https://utibeabasidan.cleverapps.io/api/")
            .build()
            .create(HeaterApiService::class.java)
    }

    val buildingApiService: BuildingApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(API_USERNAME, API_PASSWORD))
            .build()

        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .baseUrl("https://utibeabasidan.cleverapps.io/api/")
            .build()
            .create(BuildingApiService::class.java)
    }
}