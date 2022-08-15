package com.example.appproject.ui.setting

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appproject.R
import com.example.appproject.R.color.white
import com.example.appproject.ui.userManager
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.AccountRepository
import kotlin.properties.Delegates


class SettingFragment:Fragment() {
    var userNameText : String = ""
    var passwordText : String = ""
    val NOTIFICATION_ID = 0
    val CHANNEL_NAME = "channelName"
    val CHANNEL_ID = "channelID"




    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.setting_fragment, container, false)

        createNotificationChannel()


        val userRv = view.findViewById<RecyclerView>(R.id.user_rv)
        userRv.layoutManager = LinearLayoutManager(context)
        userRv.adapter = SettingAdapter(registeredUserList)

        val settingSharePref = requireActivity().getSharedPreferences("settingPref",0)
        val editor = settingSharePref.edit()




        val verifyText = Array<TextView>(10){view.findViewById(R.id.settingFragment_tv_verify0)}
        verifyText[0] = view.findViewById(R.id.settingFragment_tv_verify0)
        verifyText[1] = view.findViewById(R.id.settingFragment_tv_verify1)
        verifyText[2] = view.findViewById(R.id.settingFragment_tv_verify2)
        verifyText[3] = view.findViewById(R.id.settingFragment_tv_verify3)
        verifyText[4] = view.findViewById(R.id.settingFragment_tv_verify4)
        verifyText[5] = view.findViewById(R.id.settingFragment_tv_verify5)
        verifyText[6] = view.findViewById(R.id.settingFragment_tv_verify6)
        verifyText[7] = view.findViewById(R.id.settingFragment_tv_verify7)
        verifyText[8] = view.findViewById(R.id.settingFragment_tv_reload)
        verifyText[9] = view.findViewById(R.id.settingFragment_tv_userAnswer)

        val settingFragmentEtId : EditText = view.findViewById<EditText>(R.id.settingFragment_et_userName)
        val settingFragmentEtPwd : EditText = view.findViewById<EditText>(R.id.settingFragment_et_password)
        val settingFragmentIvPasswordSee : ImageView = view.findViewById<ImageView>(R.id.settingFragment_iv_passwordSee)
        val settingFragmentIvBeforeAgree : ImageView = view.findViewById<ImageView>(R.id.settingFragment_iv_beforeAgree)
        val settingFragmentIvAfterAgree : ImageView = view.findViewById<ImageView>(R.id.settingFragment_iv_afterAgree)
        val settingFragmentTvAgreement : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_agreement2)
        val settingFragmentTvPwdFind : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_pwdFind2)
        val settingFragmentTvRegister : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_register2)
        val settingFragmentBtLogIn : Button = view.findViewById<Button>(R.id.settingFragment_button_logIn)
        val settingFragmentFlVerify : FrameLayout = view.findViewById<FrameLayout>(R.id.settingFragment_fl_verify)
//        val userRecyclerView : RecyclerView = view.findViewById<RecyclerView>(R.id.settingFragment_rv_user)
//        userRecyclerView.layoutManager = LinearLayoutManager(context)

//        userRecyclerView.adapter = SettingAdapter(userList)

        var userNameFromUser = settingSharePref.getString("userName",null)
        var pwdFromUser = settingSharePref.getString("pwd",null)
        userManager.isAgree = settingSharePref.getBoolean("isAgree",false)
        settingFragmentEtId.setText(userNameFromUser)
        settingFragmentEtPwd.setText(pwdFromUser)
        settingFragmentTvRegister.setOnClickListener{
            goToFragment(RegisterFragment())
        }
        settingFragmentTvPwdFind.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle("密码找回")
                .setMessage("微信转账300元解锁功能")
                .create()
                .show()
        }
        settingFragmentTvAgreement.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle("用户协议")
                .setMessage("世界上只有一个中国，台湾是中国不可分割的一部分！")
                .create()
                .show()
        }
        var pwdCanBeSaw = true
        settingFragmentIvPasswordSee.setOnClickListener{
//            if(pwdCanBeSaw){
//                settingFragmentEtPwd.inputType = 1
//            }else{
//                settingFragmentEtPwd.inputType = 129
//            }
//            pwdCanBeSaw = !pwdCanBeSaw
            settingFragmentEtPwd.inputType = 1       //User now can see the password

            Handler().postDelayed({settingFragmentEtPwd.inputType = 129}, 1000) //User cannot see the password after 1 sec
        }
        settingFragmentEtId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                userNameText = p0.toString()
                editor.apply(){
                    putString("userName",userNameText)
                    apply()
                }
            }
        })
        settingFragmentEtPwd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                passwordText = p0.toString()
                editor.apply(){
                    putString("pwd",passwordText)
                    apply()
                }
            }
        })
        if(userManager.isAgree){
            settingFragmentIvBeforeAgree.visibility = INVISIBLE
            settingFragmentIvAfterAgree.visibility = VISIBLE
        }else{
            settingFragmentIvBeforeAgree.visibility = VISIBLE
            settingFragmentIvAfterAgree.visibility = INVISIBLE
        }

        settingFragmentIvBeforeAgree.setOnClickListener{
            settingFragmentIvBeforeAgree.visibility = INVISIBLE
            settingFragmentIvAfterAgree.visibility = VISIBLE
            userManager.isAgree = true
            editor.apply(){
                putBoolean("isAgree",true)
                apply()
            }
        }
        settingFragmentIvAfterAgree.setOnClickListener{
            settingFragmentIvBeforeAgree.visibility = VISIBLE
            settingFragmentIvAfterAgree.visibility = INVISIBLE
            userManager.isAgree = false
            editor.apply(){
                putBoolean("isAgree",false)
                apply()
            }
        }
        settingFragmentBtLogIn.setOnClickListener{
            if(TextUtils.isEmpty(settingFragmentEtId.text)){
                toast("请输入用户名")
            }else if(TextUtils.isEmpty(settingFragmentEtPwd.text)){
                toast("请输入密码")
            }else if(!userManager.isAgree){
                Toast.makeText(context,"请先同意用户协议",Toast.LENGTH_SHORT).show()
            }else{

                onlineCheck(userNameText,passwordText)
                showWindow(view)
                showText(verifyText)
                verifyCheck(verifyText)

            }
        }








        return view
    }




    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
            }
            val manager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun LogAfterCheck(){
        if (!userManager.isCheck){
            Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_SHORT).show()
            goToFragment(SettingFragment())
        }else{
            Toast.makeText(context,"log in", Toast.LENGTH_SHORT).show()
            userManager.logIn()
            goToFragment(LogInFragment())
            val notification = context?.let {
                NotificationCompat.Builder(it,CHANNEL_ID)
                    .setContentTitle("新闻app")
                    .setContentText("登录成功，欢迎$userNameText")
                    .setSmallIcon(androidx.constraintlayout.widget.R.drawable.notification_icon_background)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()
            }
            val notificationManager = context?.let { NotificationManagerCompat.from(it) }
            if (notification != null) {
                if (notificationManager != null) {
                    notificationManager.notify(NOTIFICATION_ID,notification)
                }
            }
            Intent(context,SettingService::class.java).also{
                activity?.startService(it)
                val dataString = userNameText
                requireActivity().intent.putExtra("LOGIN",dataString)
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun showText(verifyText: Array<TextView>) {
        val verifyWordChoose : Int = 0
        for(i in 0..7){
            verifyText[i].text = ""
            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
        }
        verifyText[0].text = verifyWord[verifyWordChoose].one
        verifyText[1].text = verifyWord[verifyWordChoose].two
        verifyText[2].text = verifyWord[verifyWordChoose].three
        verifyText[3].text = verifyWord[verifyWordChoose].four
        verifyText[4].text = verifyWord[verifyWordChoose].fakeOne
        verifyText[5].text = verifyWord[verifyWordChoose].fakeTwo
        verifyText[6].text = verifyWord[verifyWordChoose].fakeThree
        verifyText[7].text = verifyWord[verifyWordChoose].fakeFour
        var pos1 = 0
        var pos2 = 0
        var temp = ""
        for(i in 0..7){
            pos1 = (0..7).random()
            pos2 = (0..7).random()
            temp = verifyText[pos1].text.toString()
            verifyText[pos1].text = verifyText[pos2].text
            verifyText[pos2].text = temp
        }

    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    private fun verifyCheck(verifyText: Array<TextView>) {
        var userAnswer = mutableListOf<String>("*","*","*","*")
        var clickFlag = mutableListOf<Boolean>(false,false,false,false,false,false,false,false)
        var index = 0
        var pos = 0 //stand for current button
        verifyText[0].setOnClickListener{
            pos = 0
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[1].setOnClickListener{
            pos = 1
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[2].setOnClickListener{
            pos = 2
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[3].setOnClickListener{
            pos = 3
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[4].setOnClickListener{
            pos = 4
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[5].setOnClickListener{
            pos = 5
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[6].setOnClickListener{
            pos = 6
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[7].setOnClickListener{
            pos = 7
            clickFlag[pos] = !clickFlag[pos]
            if(clickFlag[pos]){
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.purple_700)) }
                if(index == 4){
                    for(i in 0..7){
                        if(verifyText[i].text == userAnswer[0]){
                            context?.let { verifyText[i].setTextColor(it.getColor(R.color.white)) }
                            clickFlag[i] = !clickFlag[i]
                        }
                    }
                    for(i in 0..2){
                        userAnswer[i] = userAnswer[i+1]
                    }
                    userAnswer[3] = verifyText[pos].text.toString()
                }else{
                    userAnswer[index++] = verifyText[pos].text.toString()
                }
            }else{
                index--
                context?.let { verifyText[pos].setTextColor(it.getColor(R.color.white)) }
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[pos].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                        }
                        userAnswer[3] = "*"
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
        verifyText[8].setOnClickListener{
            showText(verifyText)
            index = 0
            for(i in 0..7){
                clickFlag[i] = false
            }
            for(i in 0..3){
                userAnswer[i] = "*"
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
                LogAfterCheck()
            }
        }
    }

    private fun wordCheck(userAnswer: MutableList<String>, verifyWord: verifyWord): Boolean {
        if(userAnswer[0] != verifyWord.one){
            return false
        }else if(userAnswer[1] != verifyWord.two){
            return  false
        }else if(userAnswer[2] != verifyWord.three){
            return false
        }else if(userAnswer[3] != verifyWord.four){
            return false
        }else{
            return true
        }
    }


    private fun showWindow(view: View) {
        view.findViewById<FrameLayout>(R.id.settingFragment_fl_verify).visibility = VISIBLE
        view.findViewById<Button>(R.id.settingFragment_button_logIn).visibility = INVISIBLE
        view.findViewById<ImageView>(R.id.settingFragment_iv_afterAgree).visibility = INVISIBLE
        view.findViewById<EditText>(R.id.settingFragment_et_password).visibility = INVISIBLE
        view.findViewById<TextView>(R.id.settingFragment_tv_pwdFind2).visibility = INVISIBLE
        view.findViewById<TextView>(R.id.settingFragment_tv_agreement2).visibility = INVISIBLE


    }


    private fun toast(text:String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

    private fun goToFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment).commit()
    }

    private fun onlineCheck(id:String,pwd:String){
        AccountRepository.login(id,pwd,object : NetResult<User1>{

            override fun onResult(netData: NetData<User1>) {
                if(netData.data?.coinCount != null){
                    userManager.isCheck = true
                }

            }
        })
    }





    private fun refresh(){

//        AccountRepository.register("123", "123", "123", object : NetResult<Any> {
//            override fun onResult(netData: NetData<Any>) {
//                Log.d("liyu", "register json : ${netData.json}")
//               if (netData.errorCode == 0) {
//
//               }
//            }
//
//        })

//        AccountRepository.login("zhangsan12321", "123456",object : NetResult<User1>{
//            override fun onResult(netData: NetData<User1>) {
//                if (netData.errorCode == 0) {
//                    // success
//                } else {
//                    // fail
//                }
//                Log.d("maoyifan", "login json : ${netData.data?.coinCount}")
//
//                // {"data":{"admin":false,"chapterTops":[],"coinCount":10,"collectIds":[],"email":"","icon":"","id":137133,"nickname":"zhangsan12321","password":"","publicName":"zhangsan12321","token":"","type":0,"username":"zhangsan12321"},"errorCode":0,"errorMsg":""}
//            }
//
//        })
    }


//    inner class SettingAdapter(itemView: View): RecyclerView.Adapter<SettingViewHolder>(){
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
//            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.setting_fragment,parent,false)
//            return SettingViewHolder(itemView)
//        }
//
//        override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
//
//        }
//
//        override fun getItemCount(): Int {
//            return userList.size
//        }
//    }



//    class SettingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//
//    }

    private val verifyWord = listOf<verifyWord>(
        verifyWord("鸡","你","太","美","蔡","帅","徐","坤")
    )
}

data class User1(var coinCount: Int)

data class verifyWord(val one:String,val two:String,val three:String,val four:String,
                      val fakeOne:String,val fakeTwo:String,val fakeThree: String,val fakeFour:String)