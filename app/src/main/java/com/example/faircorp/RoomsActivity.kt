package com.example.faircorp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.BasicActivity
import com.example.faircorp.MainActivity
import com.example.faircorp.MainActivity.Companion.ROOM_NAME_PARAM
import com.example.faircorp.R

import com.example.faircorp.model.OnRoomSelectedListener
import com.example.faircorp.model.RoomAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomsActivity : BasicActivity(), OnRoomSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.list_rooms)
        val adapter = RoomAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { com.example.faircorp.api.ApiServices.roomApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure { response ->
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on Rooms loading $response",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


        val id = intent.getLongExtra(MainActivity.WINDOW_NAME_PARAM, 0)


    }

    override fun onRoomSelected(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).apply {
            putExtra(ROOM_NAME_PARAM, id)
        }
        startActivity(intent)
    }
}