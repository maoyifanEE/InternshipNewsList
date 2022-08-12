package com.example.appproject

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appproject.ui.home.HomeFragment
import com.example.appproject.ui.home.ViewModel
import com.example.appproject.ui.project.ProjectFragment
import com.example.appproject.ui.project.ProjectSlidePagerFragment
import com.example.appproject.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val settingFragment = SettingFragment()
    private val homeFragment = HomeFragment()
    private val projectFragment = ProjectSlidePagerFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onActivityNavigation()
    }


    private fun onActivityNavigation() {
        onReplace(homeFragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navi_button_home -> onReplace(homeFragment)
                R.id.navi_button_square -> onReplace(settingFragment)
                R.id.navi_button_project -> onReplace(projectFragment)
            }
            true
        }
    }

    private fun onReplace(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}