package com.example.faircorp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "room")
data class Room(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "current_temperature") val currentTemperature: Double?,
    @ColumnInfo(name = "target_temperature") val targetTemperature: Double?
) {
    fun toDto(): RoomDto =
        RoomDto(id.toLong(), name, currentTemperature, targetTemperature)
}

