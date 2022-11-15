package com.example.faircorp.model


import com.example.faircorp.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.RoomActivity


interface OnRoomWindowsSelectedListener {
    abstract fun onRoomWindowSelected(id: Long)
}



class RoomWindowsAdapter(val listener: RoomActivity) : RecyclerView.Adapter<RoomWindowsAdapter.RoomWindowsViewHolder>() {

    inner class RoomWindowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_room_window_name)
    }

    private val items = mutableListOf<WindowDto>()

    fun update(windows: List<WindowDto>) {
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomWindowsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_room_windows_item, parent, false)
        return RoomWindowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomWindowsViewHolder, position: Int) {
        val window = items[position]
        holder.apply {
            name.text = window.name
            itemView.setOnClickListener { listener.onRoomWindowSelected(window.id) }
        }
    }
    override fun onViewRecycled(holder: RoomWindowsViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}


