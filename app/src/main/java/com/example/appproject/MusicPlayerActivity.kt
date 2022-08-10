package com.example.appproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appproject.service.BackgroundMusicService

class MusicPlayerActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        val serviceButton = findViewById<Button>(R.id.music_service_button)
        serviceButton.text = "start"
        serviceButton.setOnClickListener {
            when (serviceButton.text) {
                "start" -> {
                    Intent(this@MusicPlayerActivity, BackgroundMusicService::class.java).also {
                        startService(it)
                        serviceButton.text = "stop"
                    }
                }
                "stop" -> {
                    Intent(this@MusicPlayerActivity,BackgroundMusicService::class.java).also {
                        stopService(it)
                        serviceButton.text = "start"
                    }
                }
            }
        }

    }


}