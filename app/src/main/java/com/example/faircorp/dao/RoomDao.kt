package com.example.faircorp.dao

import androidx.room.*
import androidx.room.Query
import com.example.faircorp.model.RoomDto
import com.example.faircorp.model.Room
import com.example.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.*


@Dao
interface RoomDao {
    @Query("SELECT * FROM room")
    fun findAll(): List<RoomDto>

    @Query("SELECT * FROM room WHERE id = :id")
    fun findById(id: Long): RoomDto

    @Query("SELECT * FROM room WHERE name = :name")
    fun findByName(name: String): RoomDto

}
