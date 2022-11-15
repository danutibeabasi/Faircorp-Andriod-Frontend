package com.example.faircorp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.faircorp.model.EnumConverters
import com.example.faircorp.model.Window

@Database(entities = [Window::class], version = 1)
@TypeConverters(EnumConverters::class)
abstract class FaircorpDatabase : RoomDatabase() {
    abstract fun windowDao(): WindowDao
}