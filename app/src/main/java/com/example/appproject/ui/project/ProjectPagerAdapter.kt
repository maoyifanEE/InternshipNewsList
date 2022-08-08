package com.example.appproject.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProjectPagerAdapter(projectFragment: Fragment) : FragmentStateAdapter(projectFragment) {

//    private lateinit var fragmentList: List<ProjectFragment>



    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return ProjectFragment()
    }







}