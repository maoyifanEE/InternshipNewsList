package com.example.appproject.ui.setting

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SettingService : Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("LOGIN")
        dataString?.let{
            Log.d("mao","special "+dataString)
        }
        return START_STICKY
    }


}