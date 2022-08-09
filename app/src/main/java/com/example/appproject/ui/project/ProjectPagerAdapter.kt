package com.example.appproject.ui.project

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.concurrent.thread

class ProjectPagerAdapter(
    projectFragment: Fragment,
) : FragmentStateAdapter(projectFragment) {


    fun isEmpty() = projectCategoryList.isEmpty()

    override fun getItemCount(): Int {
        return projectCategoryList.size
    }


    override fun createFragment(position: Int): Fragment {
        return ProjectFragment(projectCategoryList[position])
    }
}