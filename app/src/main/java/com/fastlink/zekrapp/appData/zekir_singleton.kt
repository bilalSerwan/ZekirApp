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
            _zekirs.add(
                ZekirModel(
                    id = i[0].toString().toInt(),
                    categoryId = i[1].toString().toInt(),
                    zekirTitle = i[2].toString(),
                    plainZekirTitle = i[3].toString(),
                    hint = i[4].toString(),
                    textCounter = i[5].toString(),
                    numericCounter = i[6].toString().toInt()
                )
            )
        }
    }
}
