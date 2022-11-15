package com.example.faircorp.model


enum class WindowStatus { OPEN, CLOSED}

data class WindowDto(val id: Long, val name: String, val room: RoomDto, val windowStatus: WindowStatus) {

}
