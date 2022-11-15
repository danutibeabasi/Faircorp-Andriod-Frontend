package com.example.faircorp.model


data class RoomDto(
    val id: Long,
    val name: String,
    val currentTemperature: Double?,
    val targetTemperature: Double?) {
    constructor() : this(-1, "", null, null)
}
