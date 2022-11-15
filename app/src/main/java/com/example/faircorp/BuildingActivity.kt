package com.example.faircorp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.MainActivity.Companion.BUILDING_NAME_PARAM
import com.example.faircorp.model.BuildingRoomsAdapter


import com.example.faircorp.model.OnRoomBuildingSelectedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface OnRoomBuildingSelectedListener {
    fun onRoomBuildingSelected(id: Long)
}
class BuildingActivity : BasicActivity(), OnRoomBuildingSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(BUILDING_NAME_PARAM, 0)

        val recyclerView = findViewById<RecyclerView>(R.id.list_building_rooms)
        val adapter = BuildingRoomsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { com.example.faircorp.api.ApiServices.roomApiService.findRoomsByBuildingId(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure { reponse ->
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on building rooms loading $reponse",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

    //findById(id).execute() }
    




    override fun onRoomBuildingSelected(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(BUILDING_NAME_PARAM, id)
        startActivity(intent)
    }
}

