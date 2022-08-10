package com.example.appproject.service

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.media.AsyncPlayer
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.appproject.R
import com.example.appproject.ui.util.showToast

class BackgroundMusicService : Service() {

    private val TAG = "BackgroundMusicService"

    private lateinit var musicPlayer: MediaPlayer

    init {
        Log.d(TAG,"Service is running...")
    }

    private inner class MusicServiceHandler(msg: Message) {

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        "Service Created".showToast(this)
        musicPlayer = MediaPlayer.create(applicationContext,R.raw.rickroll)
        musicPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        "Service Started".showToast(this)
        musicPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        "Service Stopped".showToast(this)
        musicPlayer.stop()
        super.onDestroy()
    }


}