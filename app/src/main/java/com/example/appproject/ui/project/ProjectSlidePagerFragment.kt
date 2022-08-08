package com.example.appproject.ui.project

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.appproject.R

class ProjectSlidePagerFragment : Fragment() {

    private lateinit var projectViewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_project_slide, container, false)
        projectViewPager = view.findViewById(R.id.project_view_pager)
        projectViewPager.isSaveEnabled = false
        val projectPagerAdapter = ProjectPagerAdapter(this)
        projectViewPager.adapter = projectPagerAdapter
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zyp","ProjectSlidePagerFragmentDestroyed")
    }
}