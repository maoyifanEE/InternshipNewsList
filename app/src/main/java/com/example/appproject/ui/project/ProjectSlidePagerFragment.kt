package com.example.appproject.ui.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.appproject.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProjectSlidePagerFragment : Fragment() {

    private lateinit var projectPagerAdapter: ProjectPagerAdapter
    private lateinit var projectViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zyp", "onCreate")

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_project_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val projectTabLayout = view.findViewById<TabLayout>(R.id.project_tab_layout)
        projectPagerAdapter = ProjectPagerAdapter(this)
        projectViewPager = view.findViewById(R.id.project_view_pager)
        projectViewPager.adapter = projectPagerAdapter
        projectViewPager.isSaveEnabled = false
        TabLayoutMediator(projectTabLayout,projectViewPager){
            tab,position -> tab.text = projectTabLayoutText[position]
        }.attach()

        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}