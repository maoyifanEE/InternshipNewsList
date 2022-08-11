package com.example.appproject.ui.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appproject.R

class SettingAdapter(val registeredUser:List<registeredUser>) : RecyclerView.Adapter<RegisteredUserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.registered_user_item,parent,false)
        return RegisteredUserViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: RegisteredUserViewHolder, position: Int) {
        val registeredUser = registeredUserList[position]
        holder.name.text = registeredUser.name


    }

    override fun getItemCount(): Int {
        return registeredUserList.size
    }

}


