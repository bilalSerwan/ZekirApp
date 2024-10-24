package com.fastlink.zekrapp.appData.model

data class ZekirCategoryModel(
    val id: Int=0,
    val zekirCategoryTitle: String="",
    val plainZekirCategoryTitle: String="",
    var isFavorite: Boolean = false
)