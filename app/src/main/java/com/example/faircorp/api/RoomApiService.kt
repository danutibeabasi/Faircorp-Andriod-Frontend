package com.example.faircorp.api

import com.example.faircorp.model.RoomDto
import retrofit2.Call
import retrofit2.http.*

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{id}") ///api/rooms/{id}
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @POST("rooms")
    fun create(@Body room: RoomDto): Call<Void>

    @GET("buildings/{buildingId}/rooms")
    fun findRoomsByBuildingId(@Path("buildingId") buildinId: Long): Call<List<RoomDto>>

    @PUT("rooms/{id}")
    fun update(@Path("id") id: Long, @Body room: RoomDto): Call<Void>

    @DELETE("rooms/{id}")
    fun delete(@Path("id") id: Long): Call<Void>
}