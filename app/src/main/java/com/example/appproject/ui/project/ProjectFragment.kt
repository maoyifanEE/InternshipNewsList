package com.example.appproject.ui.project

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appproject.MyBroadcastReceiver
import com.example.appproject.R
import com.example.appproject.ui.home.Test.title

@SuppressLint("NotifyDataSetChanged")

class ProjectFragment(private val categoryId: Int) : Fragment() {
    private val projectViewModel = ProjectViewModel()
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var progressbar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val projectBroadcastReceiver = MyBroadcastReceiver()
        val projectFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        requireActivity().registerReceiver(projectBroadcastReceiver,projectFilter)
        requireActivity().title = "Project"
        projectAdapter = ProjectAdapter(requireActivity()) {
            onReplaceFragment(it)
        }
        return inflater.inflate(R.layout.fragment_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val projectSwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.project_refresh)
        val projectRecyclerView = view.findViewById<RecyclerView>(R.id.project_recycler_view)
        projectRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        progressbar = view.findViewById(R.id.progress)

        //添加观察者
//        projectViewModel.projectData.observe(requireActivity()) {
//            progressbar.visibility = View.GONE
//            projectAdapter.refreshData(it.datas)
//            projectRecyclerView.adapter?.notifyDataSetChanged()
//        }

        projectRecyclerView.adapter = projectAdapter


//        //下拉刷新
//        projectSwipeRefreshLayout.setOnRefreshListener {
//            projectViewModel.getProjectResponse(categoryId)
//            projectSwipeRefreshLayout.isRefreshing = false
//            projectRecyclerView.adapter?.notifyDataSetChanged()
//        }

        initData()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun onReplaceFragment(url: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.project_fragment_container, ProjectDetailFragment(url))
            .addToBackStack(url)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zyp", "ProjectFragmentDestroyed")
    }


    private fun initData() {
        if (projectAdapter.isEmpty()) {
            progressbar.visibility = View.VISIBLE
        }
//        projectViewModel.getProjectResponse(categoryId)
    }
}