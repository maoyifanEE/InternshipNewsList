package com.example.appproject.ui.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.ProjectRepository


class ProjectViewModel : ViewModel() {
    private val projectResponse = MutableLiveData<ProjectData>()
    private val projectCategory = MutableLiveData<ProjectCategoryData>()
    var shareProjectData: LiveData<ProjectData> = projectResponse
    var shareProjectCategory: LiveData<ProjectCategoryData> = projectCategory
    var categoryId = 294


    private fun getProjectCategory() {
        ProjectRepository.getProjectsCategory(object : NetResult<ProjectCategoryData> {
            override fun onResult(netData: NetData<ProjectCategoryData>) {
                if (netData.errorCode == 0) {
                    //Log.d("category", netData.data.toString())
                    netData.data?.let {
                        projectCategory.postValue(it)
                    }
                }
            }
        })
    }

    private fun getProjectResponse() {

        ProjectRepository.getProjectList(1, categoryId, object : NetResult<ProjectData> {
            override fun onResult(netData: NetData<ProjectData>) {
                if (netData.errorCode == 0) {
                    netData.data?.let {
                        projectResponse.postValue(it)
                    }
                }
            }
        })
    }


    fun onRefresh() {
        getProjectCategory()
        getProjectResponse()
    }

    interface getCategoryId{
        fun getCid()
    }
}