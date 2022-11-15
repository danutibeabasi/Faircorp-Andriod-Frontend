package com.example.faircorp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : BasicActivity(), View.OnClickListener {

    companion object {
        const val WINDOW_NAME_PARAM = "https://utibeabasidan.cleverapps.io/api/windows"
        const val ROOM_NAME_PARAM = "https://utibeabasidan.cleverapps.io/api/rooms"
        const val BUILDING_NAME_PARAM = "https://utibeabasidan.cleverapps.io/api/buildings"
        const val HEATER_NAME_PARAM = "https://utibeabasidan.cleverapps.io/api/heaters"
    }


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val buttonBuildings = findViewById<Button>(R.id.buildings)
        val buttonRooms = findViewById<Button>(R.id.rooms)
        val buttonWindows = findViewById<Button>(R.id.windows)
        val buttonHeaters = findViewById<Button>(R.id.heaters)

        buttonBuildings.setOnClickListener(this)
        buttonRooms.setOnClickListener(this)
        buttonWindows.setOnClickListener(this)
        buttonHeaters.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buildings -> startActivity(
                Intent(this, BuildingsActivity::class.java)
            )

            R.id.rooms -> startActivity(
                Intent(this, RoomsActivity::class.java)
            )

            R.id.windows -> startActivity(
                Intent(this, WindowsActivity::class.java)
            )

            R.id.heaters -> startActivity(
                Intent(this, HeatersActivity::class.java)
            )
        }

    }
}