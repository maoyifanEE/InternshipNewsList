package com.example.appproject.ui

import android.util.Log
import android.widget.Toast

public class AccountManager {
    var index = 1
    var userList = listOf(
        User("18970590188","1234"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
        User("0","0"),
    )
    var isAgree:Boolean = false
    var isLogin:Boolean = false
    var isCheck:Boolean = false

    fun check(id:String,pwd:String):Int{
        var userSearch : Boolean = false
        for(pos in 0 until userList.size){
            if(id == userList[pos].userName){
                userSearch = true
            }
        }
        if(!userSearch){
            return -1
        }
        for(pos in 0 until userList.size){
            if((id == userList[pos].userName)&&(pwd == userList[pos].pwd)){
                return 1
            }
        }
        return 0
    }

    fun logIn(){
        isLogin = true
    }
    fun logOut(){
        isLogin = false
    }
    fun register(id:String,pwd:String){
        if(index < (userList.size-1)){
            userList[index].userName = id
            userList[index].pwd = pwd
            index++
        }
    }
}

val userManager = AccountManager()

data class User(var userName:String, var pwd:String)

data class UserResponse(val userName:String,val userPwd:String)