package com.fastlink.zekrapp.appData.model

data class ZekirCategoryModel(
    val id: Int,
    val categoryTitle: String,
    val plainCategoryTitle: String,
    var isFavorite: Boolean = false
)