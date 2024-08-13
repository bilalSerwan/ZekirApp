package com.fastlink.zekrapp.appData.model

data class ZekrModel(
    val id: Int,
    val categoryId: Int,
    val zekirTitle: String,
    val plainZekirTitle: String,
    val hint: String,
    val counterAsString: String,
    val counterNumber: Int
)