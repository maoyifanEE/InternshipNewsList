package com.example.appproject.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeReceiver:BroadcastReceiver() {

    val exchangeData = MutableLiveData<String>()

    fun getExchangeData(str:LiveData<String>): MutableLiveData<String> {
        return exchangeData
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        exchangeData.value = p1?.data.toString()
    }
}