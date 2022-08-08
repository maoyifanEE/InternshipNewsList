package com.example.appproject.ui.project


data class ProjectCategoryData(
    val data: List<ProjectCategory>
)

data class ProjectCategory(
    val name: String,
    val id: Int,
)

data class ProjectData(
    val curPage: Int,
    val datas: MutableList<Project>,
    val pageCount: Int,
    val size: Int
)

data class Project(
    val author: String,
    val chapterId: Int,         //cid
    val title: String,
    val desc: String,
    val envelopePic: String,
    val link: String,
    val niceDate: String
)