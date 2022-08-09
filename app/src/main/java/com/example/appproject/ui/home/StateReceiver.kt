package com.example.appproject.ui.home

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class StateReceiver(private val viewModel:ViewModel):BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        var stateOfUser = intent?.getBooleanExtra("state",false) ?: return
        viewModel.stateOfUser = stateOfUser
    }
}