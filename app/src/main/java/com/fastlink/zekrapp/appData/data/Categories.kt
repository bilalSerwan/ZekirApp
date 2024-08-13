package com.fastlink.zekrapp.appData.data

import com.fastlink.zekrapp.appData.model.CategoryModel

val categoriesData = mutableListOf<CategoryModel>()


fun getCateories(){
    for(i in categoryData){
        categoriesData.add(
            CategoryModel(
                id = i[0].toString().toInt(),
                categoryTitle = i[1].toString(),
                plainCategoryTitle = i[2].toString()
            )
        )
    }
}
