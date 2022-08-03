package com.example.appproject.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appproject.R
import com.example.appproject.ui.userManager

class LogInFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.login_fragment, container, false)
        view.findViewById<Button>(R.id.logInFragment_button).setOnClickListener{
            val settingViewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
            userManager.logOut()
            Toast.makeText(context,"Log out",Toast.LENGTH_SHORT).show()
            goToFragment(SettingFragment())
        }
        return view
    }

    private fun goToFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment).commit()
    }
}