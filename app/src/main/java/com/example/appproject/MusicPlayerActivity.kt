package com.example.appproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.appproject.service.BackgroundMusicService

class MusicPlayerActivity : AppCompatActivity() {
    var serviceStatus: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        findViewById<Button>(R.id.music_service_button).let { button ->
            button.text = "Start"
            button.setOnClickListener {
                Intent(this@MusicPlayerActivity, BackgroundMusicService::class.java)
                    .let {
                         when (serviceStatus) {
                            false -> {
                                startService(it)
                                button.text = "Stop"
                                serviceStatus = true
                            }
                            true -> {
                                stopService(it)
                                button.text = "Start"
                                serviceStatus = false
                            }

                        }
                    }
            }
        }
    }
}