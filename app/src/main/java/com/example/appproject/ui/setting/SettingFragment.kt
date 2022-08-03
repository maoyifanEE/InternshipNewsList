package com.example.appproject.ui.setting

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appproject.R
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

        val settingFragmentEtId : EditText = view.findViewById<EditText>(R.id.settingFragment_et_phonenumber)
        val settingFragmentEtPwd : EditText = view.findViewById<EditText>(R.id.settingFragment_et_password)
        val settingFragmentIvBeforeAgree : ImageView = view.findViewById<ImageView>(R.id.settingFragment_iv_beforeAgree)
        val settingFragmentIvAfterAgree : ImageView = view.findViewById<ImageView>(R.id.settingFragment_iv_afterAgree)
        val settingFragmentTvAgreement : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_agreement2)
        val settingFragmentTvPwdFind : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_pwdFind2)
        val settingFragmentTvRegister : TextView = view.findViewById<TextView>(R.id.settingFragment_tv_register2)
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
                .setMessage("没啥看的，同意就完事了")
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
        view.findViewById<Button>(R.id.settingFragment_button_logIn).setOnClickListener{
            refresh()
            if(TextUtils.isEmpty(settingFragmentEtId.text)){
                toast("请输入手机号")
            }else if(TextUtils.isEmpty(settingFragmentEtPwd.text)){
                toast("请输入密码")
            }else if(!userManager.isAgree){
                Toast.makeText(context,"请先同意用户协议",Toast.LENGTH_SHORT).show()
            }
            onlineCheck(idText,passwordText)
            if (!userManager.isCheck){
                Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_SHORT).show()
            }else{
               Toast.makeText(context,"log in", Toast.LENGTH_SHORT).show()
                userManager.logIn()
                goToFragment(LogInFragment())
           }
        }



        return view
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

}

data class User1(var coinCount: Int)