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

    var projectData: LiveData<ProjectData> = _projectResponse

    fun getProjectResponse(categoryId:Int) {

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
}