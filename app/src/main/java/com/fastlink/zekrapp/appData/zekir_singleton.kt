package com.fastlink.zekrapp.appData

import com.fastlink.zekrapp.appData.model.ZekirModel


object ZekirSingleton {
    private val _zekirs = mutableListOf<ZekirModel>()

    fun getZekirsByCategoryId(categoryId: Int) = _zekirs.filter { it.categoryId == categoryId }

    init {
        getZekrs()
    }

    private fun getZekrs() {
        for (i in zekirData) {
            _zekirs.add(i)
        }
    }
}
