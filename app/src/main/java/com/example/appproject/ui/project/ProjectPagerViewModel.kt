package com.example.appproject.ui.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.ProjectRepository

class ProjectPagerViewModel:ViewModel() {
    private val _projectCategory = MutableLiveData<ProjectCategoryData>()
    var projectCategory: LiveData<ProjectCategoryData> = _projectCategory


    fun getProjectCategory() {
        ProjectRepository.getProjectsCategory(object : NetResult<ProjectCategoryData> {
            override fun onResult(netData: NetData<ProjectCategoryData>) {
                if (netData.errorCode == 0) {
                    netData.data?.let {
                        Log.d("zyp", it.toString())
                        _projectCategory.postValue(it)
                    }
                }
            }
        })
    }
}