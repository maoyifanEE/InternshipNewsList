package com.example.appproject.ui.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroidapi.NetData
import com.example.wanandroidapi.NetResult
import com.example.wanandroidapi.repository.ProjectRepository


class ProjectViewModel : ViewModel() {
    private val _projectResponse = MutableLiveData<ProjectData>()
    private val _projectCategory = MutableLiveData<ProjectCategoryData>()
    var projectData: LiveData<ProjectData> = _projectResponse
    var projectCategory: LiveData<ProjectCategoryData> = _projectCategory
    var categoryId = 294
    var pageId = 1




    private fun getProjectCategory() {
        ProjectRepository.getProjectsCategory(object : NetResult<ProjectCategoryData> {
            override fun onResult(netData: NetData<ProjectCategoryData>) {
                if (netData.errorCode == 0) {
                    netData.data?.let {
                        Log.d("category", it.toString())
                        _projectCategory.postValue(it)
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
                        _projectResponse.postValue(it)
                    }
                }
            }
        })
    }

    fun getCid(cid: Int) {
        categoryId = cid
    }

    fun getPage(page: Int) {
        pageId = page
    }

    fun onRefresh() {
        getProjectCategory()
        getProjectResponse()
    }
}