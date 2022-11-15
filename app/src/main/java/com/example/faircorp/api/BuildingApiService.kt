package com.example.faircorp.api

import com.example.faircorp.model.BuildingDto
import retrofit2.Call
import retrofit2.http.*

interface BuildingApiService {
    @GET("buildings")
    fun findAll(): Call<List<BuildingDto>>

    @GET("buildings/{id}")
    fun findById(@Path("id") id: Long): Call<BuildingDto>

    @POST("buildings")
    fun create(@Body building: BuildingDto): Call<BuildingDto>

    @PUT("buildings/{id}")
    fun update(@Path("id") id: Long, @Body building: BuildingDto): Call<BuildingDto>

    @DELETE("buildings/{id}")
    fun delete(@Path("id") id: Long): Call<BuildingDto>

}