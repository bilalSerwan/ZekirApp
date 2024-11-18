package com.fastlink.zekrapp.appData.model

import androidx.compose.runtime.Stable

@Stable
data class ZekirModel(
    val id: Int,
    val categoryId: Int,
    val zekirTitle: String,
    val plainZekirTitle: String,
    val hint: String,
    val textCounter: String?,
    val numericCounter: Int,
    var counter :Int =0
)