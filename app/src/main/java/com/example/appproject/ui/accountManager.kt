package com.example.appproject.ui

public class AccountManager {
    var index = 1
    var userList = mutableListOf(
        User("Mao","ILoveMega!1314"),
        User("Li","Random!0879"),
        User("Liu","Random!0001"),
        User("Zhao","Just&123"),
        User("Zhou","Hello$123")
    )
    lateinit var userName: String
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

    fun userIsDifferent(userName: String):Boolean{
        var result = true
        for(pos in 0 until userList.size){
            if(userName == userList[pos].userName){
                result = false
            }
        }
        return result
    }

    fun logIn(){
        isLogin = true
    }
    fun logOut(){
        isLogin = false
    }

    /**
     * The function register is going to be test, it will failed if
     * the user name or password is empty
     * the user name is already exist
     * the user name contains sensitive or forbidden words
     * the user name is longer than 25 characters
     * the password is shorter than 8 characters
     * the password does not contain number, special symbols, uppercase and lowercase at the same time
     * the confirmed password is not the same as password
     * the password is too easy to guess
     */
    fun registerCheck(userName:String, pwd:String, pwdConfirm:String):String{
        if(userName.isEmpty()){
            return "用户名不能为空"
        }else if(pwd.isEmpty()){
            return "密码不能为空"
        }else if(pwdConfirm.isEmpty()){
            return "必须确认密码"
        }else if(!userNameIsValid(userName)){
            return "用户名包含敏感词汇"
        }else if(userName.length > 25){
            return "用户名长度不能超过25个字符"
        }else if(pwd.length < 8){
            return "密码不能短于8个字符"
        }else if(!pwdIsValid(pwd)){
            return "密码必须包含数字，大小写和特殊符号"
        }else if(pwdConfirm != pwd){
            return "密码核对失败"
        }else if(pwdIsTooEasy(pwd,userName)){
            return "密码过于简单"
        }else{
            return "允许注册"
        }
    }

    /**
     * The definition for simple password is following:
     * Contains username
     * Contains more than two consecutive numbers
     * Contains high frequency word
     * The length is shorter than 10
     */
    private fun pwdIsTooEasy(pwd: String,userName: String): Boolean {
        if(pwd.contains(userName)){
            return true
        }else if(pwd.length < 10){
            return true
        }else if(pwdContainsHighFrequencyWord(pwd)){
            return true
        }else if(pwdContainsConsecutiveNumber(pwd)){
            return true
        }
        return false
    }

    private fun pwdContainsConsecutiveNumber(pwd: String): Boolean {
        for(i in pwd.indices){
            if((pwd[i] in '0'..'9') && (i <= (pwd.length-2))){
                if((pwd[i+1] == pwd[i]+1) && (pwd[i+2] == pwd[i]+2)){
                    return true
                }
                if((pwd[i+1] == pwd[i]-1) && (pwd[i+2] == pwd[i]-2)){
                    return true
                }
            }
        }
        return false
    }

    private fun pwdContainsHighFrequencyWord(pwd: String): Boolean {
        for(i in highFrequencyWords.indices){
            if(pwd.contains(highFrequencyWords[i])){
                return true
            }
        }
        return false
    }

    private fun pwdIsValid(pwd: String): Boolean {
        if(pwd.count{it.isDigit()} < 1){
            return false
        }else if(pwd.count{it.isUpperCase()} < 1){
            return false
        }else if(pwd.count{it.isLowerCase()} < 1){
            return false
        }else if((pwd.length - pwd.count{it.isLetterOrDigit()}) < 1){
            return false
        }else{
            return true
        }
    }

    private fun userNameIsValid(userName: String):Boolean{
        for(i in sensitiveWords.indices){
            if(userName.contains(sensitiveWords[i])){
                return false
            }
        }
        return true
    }

    private val sensitiveWords = listOf<String>(
        "fuck","bitch","jerk","pervert"
    )
    private val highFrequencyWords = listOf<String>(
        "hello","Hello","Hi","hi"
    )

}

val userManager = AccountManager()

data class User(var userName:String, var pwd:String)

data class UserResponse(val userName:String,val userPwd:String)