package com.example.faircorp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity(tableName = "rwindow")
data class Window(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo val name: String,
        @ColumnInfo(name = "room_id") val roomId: Int,
        @ColumnInfo(name = "room_name") val roomName: String,
        @ColumnInfo(name = "window_status") val windowStatus: WindowStatus
) {
        fun toDto(): WindowDto =
            WindowDto(id.toLong(), name, RoomDto(roomId.toLong(), roomName, null, null), windowStatus)
}
