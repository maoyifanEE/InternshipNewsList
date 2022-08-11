package com.example.appproject.ui.setting
//This note just for update test
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appproject.R
import com.example.appproject.ui.User
import com.example.appproject.ui.userManager
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.AccountRepository

class RegisterFragment:Fragment() {
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.register_fragment, container, false)

        var userName : String = ""
        var verification : Int
        var pwd : String = ""
        var pwdComfirm : String = ""

        val registerFragmentButtonVerify = view.findViewById<Button>(R.id.registerFragment_button_verify)
        val registerFragmentTvQuit = view.findViewById<TextView>(R.id.registerFragment_tv_quit)
        val registerFragmentTvRegister = view.findViewById<TextView>(R.id.registerFragment_tv_register)
        val registerFragmentEtPhoneNum = view.findViewById<EditText>(R.id.registerFragment_et_phoneNumber)
        val registerFragmentEtVerify = view.findViewById<EditText>(R.id.registerFragment_et_verify)
        val registerFragmentEtPwd= view.findViewById<EditText>(R.id.registerFragment_et_pwd)
        val registerFragmentEtPwdComfirm = view.findViewById<EditText>(R.id.registerFragment_et_pwdComfirm)

        registerFragmentTvQuit.setOnClickListener{
            goToFragment(SettingFragment())
        }

        registerFragmentButtonVerify.setOnClickListener(){
            AlertDialog.Builder(context)
                .setTitle("验证码")
                .setMessage("123456")
                .create()
                .show()
        }

        registerFragmentEtPhoneNum.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                 userName = p0.toString()
            }
        })
        registerFragmentEtVerify.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                verification = p0.toString().toInt()
            }
        })
        registerFragmentEtPwd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                pwd = p0.toString()
            }
        })
        registerFragmentEtPwdComfirm.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                pwdComfirm = p0.toString()
            }
        })
        registerFragmentTvRegister.setOnClickListener{
            var string = userManager.registerCheck(userName,pwd,pwdComfirm)
            if(string == "允许注册"){
                onlineRegister(userName,pwd,pwdComfirm)
            }else{
                Toast.makeText(context,string,Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun onlineRegister(id:String,pwd:String,pwdConfirm:String){
        AccountRepository.register(id, pwd, pwdConfirm, object : NetResult<User> {
            override fun onResult(netData: NetData<User>) {
//                Log.d("liyu", "register json : ${netData.json}")
                if(netData.errorMsg == ""){
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, netData.errorMsg, Toast.LENGTH_SHORT).show()
                    Log.d("register",netData.errorMsg)
                }
            }
        })
    }

    private fun goToFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment).commit()
    }
}