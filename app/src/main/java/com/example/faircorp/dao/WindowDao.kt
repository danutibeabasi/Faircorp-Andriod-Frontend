package com.example.faircorp.dao

import androidx.room.*
import com.example.faircorp.model.Window

@Dao
interface WindowDao {
    @Query("select * from rwindow order by name")
    fun findAll(): List<Window>

    @Query("select * from rwindow where id = :windowId")
    fun findById(windowId: Int): Window

    @Insert
    suspend fun create(window: Window)

    @Update
    suspend fun update(window: Window): Int

    @Delete
    suspend fun delete(window: Window)

    @Query("delete from rwindow")
    suspend fun clearAll()
}