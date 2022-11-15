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
import com.example.faircorp.WindowActivity
import com.example.faircorp.model.RoomWindowsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomActivity : BasicActivity(), OnRoomWindowSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(ROOM_NAME_PARAM, 0)

        val recyclerView = findViewById<RecyclerView>(R.id.list_room_windows)
        val adapter = RoomWindowsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { com.example.faircorp.api.ApiServices.windowApiService.findWindowsByRoomId(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure { response ->
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on room windows loading $response",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onRoomWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(MainActivity.WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }


}


interface OnRoomWindowSelectedListener {
    abstract fun onRoomWindowSelected(id: Long)
}

