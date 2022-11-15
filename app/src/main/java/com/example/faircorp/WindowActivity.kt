package com.example.faircorp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.faircorp.MainActivity.Companion.WINDOW_NAME_PARAM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        val textView = findViewById<TextView>(R.id.txt_window_name)
        val textView2 = findViewById<TextView>(R.id.txt_room_name)
        val textView3 = findViewById<TextView>(R.id.txt_window_current_temperature)
        val textView4 = findViewById<TextView>(R.id.txt_window_target_temperature)
        val textView5 = findViewById<TextView>(R.id.txt_window_status)
        textView.text = "Window $id"
        textView2.text = "Room $id"
        textView3.text = "Current temperature $id"
        textView4.text = "Target temperature $id"
        textView5.text = "Status $id"
    }

    fun switchStatus(view: View) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching {
                val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
                com.example.faircorp.api.ApiServices.windowApiService.switchStatus(id).execute()
            }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        findViewById<TextView>(R.id.txt_window_status).text =
                            it.body()?.windowStatus.toString()
                    }
                }
                .onFailure { response ->
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on Window loading $response",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }
}
