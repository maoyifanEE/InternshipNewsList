package com.example.appproject.ui.setting

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appproject.MusicPlayerActivity
import com.example.appproject.R
import com.example.appproject.R.color.white
import com.example.appproject.ui.userManager
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.AccountRepository


class SettingFragment:Fragment() {
    var idText : String = "0"
    var passwordText : String = "0"


    private lateinit var settingViewModel:SettingViewModel

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.setting_fragment, container, false)

        settingViewModel = ViewModelProvider(requireActivity()).get(SettingViewModel::class.java)
        if(context != null){
            val myContext = context
        }

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

        settingFragmentTvRegister.setOnClickListener{
            goToFragment(RegisterFragment())
        }
        settingFragmentTvPwdFind.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle("密码找回")
                .setMessage(idText.toString())
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
        settingFragmentEtId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                idText = p0.toString()
            }
        })
        settingFragmentEtPwd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                passwordText = p0.toString()
            }
        })
        settingFragmentIvBeforeAgree.setOnClickListener{
            settingFragmentIvBeforeAgree.visibility = INVISIBLE
            settingFragmentIvAfterAgree.visibility = VISIBLE
            userManager.isAgree = true
        }
        settingFragmentIvAfterAgree.setOnClickListener{
            settingFragmentIvBeforeAgree.visibility = VISIBLE
            settingFragmentIvAfterAgree.visibility = INVISIBLE
            userManager.isAgree = false
        }
        settingFragmentBtLogIn.setOnClickListener{
//            if(TextUtils.isEmpty(settingFragmentEtId.text)){
//                toast("请输入用户名")
//            }else if(TextUtils.isEmpty(settingFragmentEtPwd.text)){
//                toast("请输入密码")
//            }else if(!userManager.isAgree){
//                Toast.makeText(context,"请先同意用户协议",Toast.LENGTH_SHORT).show()
//            }else{
//                onlineCheck(idText,passwordText)
            showWindow(view)
            showText(verifyText)
            verifyCheck(verifyText)

//        Intent().also{intent ->
//            intent.action = "com.example.broadcast.SETTING_NOTIFICATION"
//            intent.putExtra("data","Notice me!")
//            context?.let { it1 -> LocalBroadcastManager.getInstance(it1).sendBroadcast(intent) }
//        }
//                if (!userManager.isCheck){
//                    Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context,"log in", Toast.LENGTH_SHORT).show()
//                    userManager.logIn()
//                    goToFragment(LogInFragment())
//                }
//            }




        }
        requireActivity().title = "Setting"



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val musicActivityButton = view.findViewById<View>(R.id.music_activity_text_view)
        musicActivityButton.setOnClickListener {
            val startMusicActivityIntent = Intent(requireActivity(),MusicPlayerActivity::class.java)
            startActivity(startMusicActivityIntent)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("ResourceAsColor")
    private fun showText(verifyText: Array<TextView>) {
        val i : Int = 0
        for(i in 0..7){
            verifyText[i].text = ""
            verifyText[i].setTextColor(R.color.white)
        }
        var tem1:Int
        var tem2:Int
        var tem3:Int
        var tem4:Int
        tem1 = (0..7).random()
        do{
            tem2 = (0..7).random()
        }while (tem2 == tem1)
        do{
            tem3 = (0..7).random()
        }while ((tem3 == tem2) || (tem3 == tem1))
        do{
            tem4 = (0..7).random()
        }while ((tem4 == tem2) || (tem4 == tem1) || (tem4 == tem3))
        verifyText[tem1].text = verifyWord[i].one
        verifyText[tem2].text = verifyWord[i].two
        verifyText[tem3].text = verifyWord[i].three
        verifyText[tem4].text = verifyWord[i].four
    }

    @SuppressLint("ResourceAsColor")
    private fun verifyCheck(verifyText: Array<TextView>) {
        var userAnswer = mutableListOf<String>("*","*","*","*")
        var clickFlag = mutableListOf<Boolean>(false,false,false,false,false,false,false,false)
        var index = 0
        verifyText[0].setOnClickListener{
            clickFlag[0] = !clickFlag[0]
            if(clickFlag[0]){
                context?.let { verifyText[0].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[0].text.toString()
            }else{
                index--
                verifyText[0].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[0].text.toString()){
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
            }
        }
        verifyText[1].setOnClickListener{
            clickFlag[1] = !clickFlag[1]
            if(clickFlag[1]){
                context?.let { verifyText[1].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[1].text.toString()
            }else{
                index--
                verifyText[1].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[1].text.toString()){
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
            }
        }
        verifyText[2].setOnClickListener{
            clickFlag[2] = !clickFlag[2]
            if(clickFlag[2]){
                context?.let { verifyText[2].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[2].text.toString()
            }else{
                index--
                verifyText[2].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[2].text.toString()){
                        if(i == 3){
                            userAnswer[i] = "*"
                        }else{
                            for(j in i..2){
                                userAnswer[j] = userAnswer[j+1]
                            }
                            userAnswer[3] = "*"
                        }
                    }
                }
            }
            verifyText[9].text = userAnswer[0]+userAnswer[1]+userAnswer[2]+userAnswer[3]
            if(wordCheck(userAnswer,verifyWord[0])){
                verifyText[9].text = "success"
            }
        }
        verifyText[3].setOnClickListener{
            clickFlag[3] = !clickFlag[3]
            if(clickFlag[3]){
                context?.let { verifyText[3].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[3].text.toString()
            }else{
                index--
                verifyText[3].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[3].text.toString()){
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
            }
        }
        verifyText[4].setOnClickListener{
            clickFlag[4] = !clickFlag[4]
            if(clickFlag[4]){
                context?.let { verifyText[4].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[4].text.toString()
            }else{
                index--
                verifyText[4].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[4].text.toString()){
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
            }
        }
        verifyText[5].setOnClickListener{
            clickFlag[5] = !clickFlag[5]
            if(clickFlag[5]){
                context?.let { verifyText[5].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[5].text.toString()
            }else{
                index--
                verifyText[5].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[5].text.toString()){
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
            }
        }
        verifyText[6].setOnClickListener{
            clickFlag[6] = !clickFlag[6]
            if(clickFlag[6]){
                context?.let { verifyText[6].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[6].text.toString()
            }else{
                index--
                verifyText[6].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[6].text.toString()){
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
            }
        }
        verifyText[7].setOnClickListener{
            clickFlag[7] = !clickFlag[7]
            if(clickFlag[7]){
                context?.let { verifyText[7].setTextColor(it.getColor(R.color.black)) }
                userAnswer[index++] = verifyText[7].text.toString()
            }else{
                index--
                verifyText[7].setTextColor(white)
                for(i in 0..3){
                    if(userAnswer[i] == verifyText[7].text.toString()){
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
            }
        }
    }

    private fun wordCheck(userAnswer: MutableList<String>, verifyWord: verifyWord): Boolean {
        return if(userAnswer[0] != verifyWord.one){
            false
        }else if(userAnswer[1] != verifyWord.two){
            false
        }else if(userAnswer[2] != verifyWord.three){
            false
        }else userAnswer[3] == verifyWord.four
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
    override fun onDestroy() {
        super.onDestroy()
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
