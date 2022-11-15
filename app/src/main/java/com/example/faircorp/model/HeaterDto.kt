package com.example.faircorp.model



enum class HeaterStatus {
    ON, OFF
}


data class HeaterDto (
    val id: Long?,
    val name: String?,
    val power: Int?,
    val heaterStatus: HeaterStatus,
    val room: RoomDto?
)
