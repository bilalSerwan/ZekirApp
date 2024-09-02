package com.fastlink.zekrapp.di

import com.fastlink.zekrapp.appData.model.ZekirModel
import com.fastlink.zekrapp.appData.zekirData
import javax.inject.Singleton

object ZekirSingleton {
    private val _zekirs = mutableListOf<ZekirModel>()
    fun getZekirsByCategoryId(categoryId: Int) = _zekirs.filter { it.categoryId == categoryId }

    init {
        addZekrs()
    }

    private fun addZekrs() {
        for (i in zekirData) {
            _zekirs.add(i)
        }
    }
}
