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

val projectCategoryList = listOf(
    294,
    402,
    367,
    323,
    314,
    358,
    328,
    331,
    336,
    337,
    338,
    339,
    340,
    357,
    363,
    380,
    382,
    383,
    310,
    385,
    387,
    388,
    391,
    400,
    401,
    312,
    526,
    539
)

val projectTabLayoutText = listOf<String>(
    "完整项目",
    "跨平台应用",
    "资源聚合类",
    "动画",
    "RV列表动效",
    "项目基础功能",
    "网络&文件下载",
    "TextView",
    "键盘",
    "快应用",
    "日历&时钟",
    "K线图",
    "硬件相关",
    "表格类",
    "创意汇",
    "ImageView",
    "音视频&相机",
    "相机",
    "下拉刷新",
    "架构",
    "对话框",
    "数据库",
    "AS插件",
    "ViewPager",
    "二维码",
    "富文本编辑器",
    "IM",
    "未分类"

)