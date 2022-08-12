package com.example.appproject.ui.home

// 此文件用于点击新闻后之后进入的详细界面，其中有WebView，标题，以及,时间构成

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.appproject.R


class ContentFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        //

        val view:View = inflater.inflate((R.layout.fragment_detail_home),container,false)
        displayContent(view)
        return view
    }

    // create viewModel and obtain shared data
    private lateinit var viewModel: ViewModel
    private fun displayContent(view:View) {
        val content:WebView = view.findViewById(R.id.webview)
        val time:TextView = view.findViewById(R.id.content_time)
        val title:TextView = view.findViewById(R.id.content_title)

        viewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]
        viewModel.news.observe(viewLifecycleOwner) {
            //set text based on the ViewModel Livedata

            time.text = it.niceDate
            title.text = it.title
            content.loadUrl(it.link)
        }
    }

}
