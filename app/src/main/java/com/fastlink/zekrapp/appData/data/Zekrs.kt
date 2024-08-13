package com.fastlink.zekrapp.appData.data

import com.fastlink.zekrapp.appData.model.ZekrModel

val zekrs = mutableListOf<ZekrModel>()

fun getZekrs(){
    for(i in zikerData){
        zekrs.add(
            ZekrModel(
                id = i[0].toString().toInt(),
                categoryId = i[1].toString().toInt(),
                zekirTitle = i[2].toString(),
                plainZekirTitle = i[3].toString(),
                hint = i[4].toString(),
                counterAsString = i[5].toString(),
                counterNumber = i[6].toString().toInt()
            )
        )
    }
}